package lijewski.demodb.presentation.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.domain.usecase.employee.GetAllEmployeesUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAllEmployeesUseCase: GetAllEmployeesUseCase
) : ViewModel() {
    val isLoading = ObservableBoolean(false)
    val employeesList : MutableLiveData<List<Employee>> by lazy { MutableLiveData<List<Employee>>() }
    val error : MutableLiveData<Throwable> by lazy { MutableLiveData<Throwable>() }

    fun returnAllEmployees() {
        getAllEmployeesUseCase.execute {
            onComplete {
                employeesList.value = it
            }
            onError {
                error.value = it
            }
        }
    }

    override fun onCleared() {
        getAllEmployeesUseCase.unsubscribe()
    }

}
