package lijewski.demodb.presentation.dashboard

import lijewski.demodb.domain.usecase.employee.GetAllEmployeesUseCase
import lijewski.demodb.presentation.base.BaseEmployeeViewModel
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val getAllEmployeesUseCase: GetAllEmployeesUseCase
) : BaseEmployeeViewModel() {

    fun returnAllEmployees() {
        isLoading.value = true
        getAllEmployeesUseCase.execute {
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
        getAllEmployeesUseCase.unsubscribe()
    }

}
