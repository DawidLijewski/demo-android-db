package lijewski.demodb.domain.usecase.employee

import lijewski.demodb.domain.common.BaseUseCase
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.domain.repository.EmployeeRepository
import javax.inject.Inject

class AddEmployeeUseCase @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : BaseUseCase<Unit>() {
    lateinit var newEmployee: Employee
    override suspend fun executeOnBackground() {
        return employeeRepository.addEmployee(newEmployee)
    }
}
