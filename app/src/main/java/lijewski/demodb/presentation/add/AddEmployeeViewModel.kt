package lijewski.demodb.presentation.add

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.domain.model.Gender
import lijewski.demodb.domain.usecase.employee.AddEmployeeUseCase
import lijewski.demodb.ext.Event
import lijewski.demodb.presentation.base.BaseEmployeeViewModel
import java.time.LocalDate
import javax.inject.Inject


class AddEmployeeViewModel @Inject constructor(
    private val addEmployeeUseCase: AddEmployeeUseCase
) : BaseEmployeeViewModel() {
    val eventAddSuccess = MutableLiveData<Event<Any>>()

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val birthDate = MutableLiveData<LocalDate>()
    val gender = MutableLiveData<Gender>(Gender.NONE)
    //TODO: add address list

    val newEmployeeValid = MediatorLiveData<Boolean>().apply {
        val validator = EmployeeValidator(::postValue)
        addSource(firstName, validator as Observer<in String>)
        addSource(lastName, validator as Observer<in String>)
        addSource(birthDate, validator as Observer<in LocalDate>)
        addSource(gender, validator as Observer<in Gender>)
    }

    private inner class EmployeeValidator(private val validationConsumer: (Boolean) -> Unit) :
        Observer<Any> {
        override fun onChanged(ignored: Any?) {
            val firstName = firstName.value
            val lastName = lastName.value
            val birth = birthDate.value
            val gender = gender.value
            validationConsumer(when {
                firstName.isNullOrBlank() -> false
                lastName.isNullOrBlank() -> false
                birth == null -> false
                gender == null -> false
                gender == Gender.NONE -> false
                else -> true
            })
        }
    }

    fun addNewEmployee() {
        if (newEmployeeValid.value == false) {
            return
        }
        val employee = Employee(
            firstName = firstName.value!!,
            lastName = lastName.value!!,
            birthdate = birthDate.value!!,
            gender = gender.value!!
        )

        isLoading.value = true
        addEmployeeUseCase.newEmployee = employee
        addEmployeeUseCase.execute {
            onComplete { handleSuccessfulNewEmployee() }
            onError { handleError(it) }
            onCancel { handleCancelation(it) }
        }
    }

    private fun handleSuccessfulNewEmployee() {
        eventAddSuccess.value = Event(Any())
    }

    override fun onCleared() {
        newEmployeeValid.removeSource(firstName)
        newEmployeeValid.removeSource(lastName)
        newEmployeeValid.removeSource(birthDate)
        newEmployeeValid.removeSource(gender)
        addEmployeeUseCase.unsubscribe()
    }

}
