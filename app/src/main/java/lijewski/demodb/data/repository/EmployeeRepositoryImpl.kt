package lijewski.demodb.data.repository

import lijewski.demodb.data.source.db.EmployeeDao
import lijewski.demodb.data.source.mapper.EmployeeMapper
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.domain.repository.EmployeeRepository
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val employeeMapper: EmployeeMapper
) : EmployeeRepository {
    override suspend fun addEmployee(employee: Employee) {
        employeeDao.addEmployee(employeeMapper.mapToEntity(employee))
    }

    override suspend fun addEmployeeList(employeeList: List<Employee>) {
        val employeeEntityList = employeeMapper.mapToEntityList(employeeList)
        employeeDao.addEmployee(employeeEntityList)
    }

    override suspend fun editEmployee(employee: Employee) {
        employeeDao.editEmployee(employeeMapper.mapToEntity(employee))
    }

    override suspend fun editEmployeeList(employeeList: List<Employee>) {
        val employeeEntityList = employeeMapper.mapToEntityList(employeeList)
        employeeDao.editEmployee(employeeEntityList)
    }

    override suspend fun removeEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employeeMapper.mapToEntity(employee))
    }

    override suspend fun removeEmployeeList(employeeList: List<Employee>) {
        val employeeEntityList = employeeMapper.mapToEntityList(employeeList)
        employeeDao.deleteEmployee(employeeEntityList)
    }

    override suspend fun getEmployee(employeeQuery: Employee): List<Employee> {
        val employeeEntityList = employeeDao.getEmployeeByName(employeeQuery.firstName, employeeQuery.lastName)
        return employeeMapper.mapFromEntityList(employeeEntityList)
    }

    override suspend fun selectAllEmployees(): List<Employee> {
        val employeeEntityList = employeeDao.getAllEmployees()
        return employeeMapper.mapFromEntityList(employeeEntityList)
    }
}
