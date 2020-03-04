package lijewski.demodb.domain.repository

import lijewski.demodb.domain.model.Employee

interface EmployeeRepository {
    suspend fun addEmployee(employee: Employee)
    suspend fun addEmployeeList(employeeList: List<Employee>)
    suspend fun editEmployee(employee: Employee)
    suspend fun editEmployeeList(employeeList: List<Employee>)
    suspend fun deleteEmployee(employee: Employee)
    suspend fun deleteEmployeeList(employeeList: List<Employee>)
    suspend fun getEmployee(firstName: String, lastName: String): List<Employee>
    suspend fun selectAllEmployees(): List<Employee>
}
