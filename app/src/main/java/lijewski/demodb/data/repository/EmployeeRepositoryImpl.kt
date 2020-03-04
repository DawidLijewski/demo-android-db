package lijewski.demodb.data.repository

import lijewski.demodb.data.source.db.EmployeeDao
import lijewski.demodb.data.source.entity.EmployeeEntity
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
        val employeeEntityList = mapEmployeeListToEntitiyList(employeeList)
        employeeDao.addEmployee(employeeEntityList)
    }

    override suspend fun editEmployee(employee: Employee) {
        employeeDao.editEmployee(employeeMapper.mapToEntity(employee))
    }

    override suspend fun editEmployeeList(employeeList: List<Employee>) {
        val employeeEntityList = mapEmployeeListToEntitiyList(employeeList)
        employeeDao.editEmployee(employeeEntityList)
    }

    override suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employeeMapper.mapToEntity(employee))
    }

    override suspend fun deleteEmployeeList(employeeList: List<Employee>) {
        val employeeEntityList = mapEmployeeListToEntitiyList(employeeList)
        employeeDao.deleteEmployee(employeeEntityList)
    }

    override suspend fun getEmployee(firstName: String, lastName: String): List<Employee> {
        val employeeEntityList = employeeDao.getEmployee(firstName, lastName)
        return mapEntityListToEmployee(employeeEntityList)
    }

    override suspend fun selectAllEmployees(): List<Employee> {
        val employeeEntityList = employeeDao.getAllEmployees()
        return mapEntityListToEmployee(employeeEntityList)
    }

    //TODO: move to mapper
    private fun mapEntityListToEmployee(employeeEntityList: List<EmployeeEntity>): List<Employee> {
        return employeeEntityList.map {
            employeeMapper.mapFromEntity(it)
        }
    }
    //TODO: move to mapper
    private fun mapEmployeeListToEntitiyList(employeeList: List<Employee>): List<EmployeeEntity> {
        return employeeList.map {
            employeeMapper.mapToEntity(it)
        }
    }
}
