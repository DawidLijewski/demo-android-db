package lijewski.demodb.presentation.search

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.domain.usecase.employee.GetEmployeeUseCase
import lijewski.demodb.presentation.base.BaseEmployeeViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getEmployeeUseCase: GetEmployeeUseCase
) : BaseEmployeeViewModel() {

    val firstName = MutableLiveData<String>("")
    val lastName = MutableLiveData<String>("")

    val queryValid = MediatorLiveData<Boolean>().apply {
        val validator = QueryValidator(::postValue)
        addSource(firstName, validator as Observer<String>)
        addSource(lastName, validator as Observer<String>)
    }

    private inner class QueryValidator(private val validationConsumer: (Boolean) -> Unit) :
        Observer<String> {
        override fun onChanged(value: String?) {
            validationConsumer(
                when {
                    value == null -> false
                    value.isNotBlank() -> true
                    else -> false
                }
            )
        }
    }

    fun startSearch() {
        if (queryValid.value == true) {
            searchEmployee(Employee(firstName = firstName.value!!, lastName = lastName.value!!))
        }
    }

    fun searchEmployee(employee: Employee) {
        isLoading.value = true
        getEmployeeUseCase.employeeQuery = employee
        getEmployeeUseCase.execute {
            onComplete {
                handleSuccess(it)
            }
            onError {
                handleError(it)
            }
            onCancel {
                handleCancelation(it)
            }
        }
    }

    override fun onCleared() {
        queryValid.removeSource(firstName)
        queryValid.removeSource(lastName)
        getEmployeeUseCase.unsubscribe()
    }

}
