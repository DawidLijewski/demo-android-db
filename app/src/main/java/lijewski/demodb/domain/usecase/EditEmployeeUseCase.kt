package lijewski.demodb.domain.usecase

import lijewski.demodb.domain.common.BaseUseCase
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.domain.repository.EmployeeRepository
import javax.inject.Inject

class EditEmployeeUseCase @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : BaseUseCase<Unit>() {
    lateinit var employee: Employee
    override suspend fun executeOnBackground() {
        return employeeRepository.editEmployee(employee)
    }
}
