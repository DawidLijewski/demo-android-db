package lijewski.demodb.domain.usecase

import lijewski.demodb.domain.common.BaseUseCase
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.domain.repository.EmployeeRepository
import javax.inject.Inject

class EditEmployeeListUseCase @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : BaseUseCase<Unit>() {
    var employeeList: List<Employee> = arrayListOf()
    override suspend fun executeOnBackground() {
        return employeeRepository.addEmployeeList(employeeList)
    }
}
