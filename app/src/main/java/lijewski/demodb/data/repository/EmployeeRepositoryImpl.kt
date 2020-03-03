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
    override suspend fun saveEmployee(employee: Employee): Long {
        employeeDao.insertEmployee(employeeMapper.mapToEntity(employee))
        return 0L
    }

    override suspend fun selectAllEmployees(): List<Employee> {
        val employeeEntityList = employeeDao.selectAllEmployees()
        return employeeEntityList.map {
            employeeMapper.mapFromEntity(it)
        }
    }
}
