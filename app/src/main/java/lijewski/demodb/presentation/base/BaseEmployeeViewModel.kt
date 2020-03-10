package lijewski.demodb.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lijewski.demodb.domain.model.Employee
import java.util.concurrent.CancellationException

abstract class BaseEmployeeViewModel : ViewModel() {
    val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val employeeList: MutableLiveData<List<Employee>> by lazy {
        MutableLiveData<List<Employee>>().apply { emptyList<Employee>() }
    }
    val error: MutableLiveData<Throwable> by lazy {
        MutableLiveData<Throwable>()
    }

    protected fun handleSuccess(employeeList: List<Employee>) {
        isLoading.value = false
        this.employeeList.value = employeeList
    }

    protected fun handleError(t: Throwable) {
        isLoading.value = false
        error.value = t
    }

    protected fun handleCancelation(ce: CancellationException) {
        isLoading.value = false
        //TODO: handle cancelation, maybe in onError?
    }
}
