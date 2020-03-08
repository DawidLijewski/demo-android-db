package lijewski.demodb.presentation.add

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
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

    val firstName: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also { it.value = "" }
    }
    val lastName: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also { it.value = "" }
    }

    val birthDate: MutableLiveData<LocalDate> by lazy { MutableLiveData<LocalDate>() }
    val gender: MutableLiveData<Gender> by lazy {
        MutableLiveData<Gender>().also { it.value = Gender.NONE }
    }
    //TODO: add address list

    val employeeMediator = MediatorLiveData<Employee>()

    init {
        employeeMediator.addSource(firstName) {
            if (it.isNotBlank()) {
                employeeMediator.value?.firstName = it
            }
        }
        employeeMediator.addSource(lastName) {
            if (it.isNotBlank()) {
                employeeMediator.value?.lastName = it
            }
        }
        employeeMediator.addSource(birthDate) {
            it?.let { employeeMediator.value?.birthdate = it }
        }
        employeeMediator.addSource(gender) {
            if (it != null && it != Gender.NONE) {
                employeeMediator.value?.gender = it
            }
        }
    }

    fun checkIsNewEmployeeCorrect(): Boolean {
        return employeeMediator.value?.firstName.isNullOrBlank() &&
                employeeMediator.value?.lastName.isNullOrBlank() &&
                employeeMediator.value?.birthdate != null &&
                employeeMediator.value?.gender != Gender.NONE
    }

    fun addNewEmployee() {
        employeeMediator.value?.let { employee ->
            isLoading.value = true
            addEmployeeUseCase.newEmployee = employee
            addEmployeeUseCase.execute {
                onComplete { handleSuccessfulNewEmployee() }
                onError { handleError(it) }
                onCancel { handleCancelation(it) }
            }
        }
    }

    private fun handleSuccessfulNewEmployee() {
        eventAddSuccess.value = Event(Any())
    }

    override fun onCleared() {
        employeeMediator.removeSource(firstName)
        employeeMediator.removeSource(lastName)
        employeeMediator.removeSource(birthDate)
        employeeMediator.removeSource(gender)
        addEmployeeUseCase.unsubscribe()
    }

}
