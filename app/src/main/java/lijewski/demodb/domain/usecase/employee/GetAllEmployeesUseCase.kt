package lijewski.demodb.domain.usecase.employee

import lijewski.demodb.domain.common.BaseUseCase
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.domain.repository.EmployeeRepository
import javax.inject.Inject

class GetAllEmployeesUseCase @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : BaseUseCase<List<Employee>>() {
    override suspend fun executeOnBackground(): List<Employee> {
        return employeeRepository.selectAllEmployees()
    }
}
