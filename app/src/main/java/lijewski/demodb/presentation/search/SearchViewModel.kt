package lijewski.demodb.presentation.search

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.domain.usecase.employee.GetEmployeeUseCase
import lijewski.demodb.presentation.base.BaseEmployeeViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getEmployeeUseCase: GetEmployeeUseCase
) : BaseEmployeeViewModel() {

    val firstName: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also { it.value = "" }
    }
    val lastName: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also { it.value = "" }
    }

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
    }

    fun checkIsQueryCorrect(): Boolean {
        return employeeMediator.value?.firstName.isNullOrBlank() ||
                employeeMediator.value?.lastName.isNullOrBlank()
    }

    fun searchEmployee() {
        employeeMediator.value?.let { employee ->
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
    }

    override fun onCleared() {
        employeeMediator.removeSource(firstName)
        employeeMediator.removeSource(lastName)
        getEmployeeUseCase.unsubscribe()
    }

}
