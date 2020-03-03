package lijewski.demodb.domain.repository

import lijewski.demodb.domain.model.Employee

interface EmployeeRepository {
    suspend fun saveEmployee(employee: Employee): Long
    suspend fun selectAllEmployees(): List<Employee>
}
