package lijewski.demodb.data.source.db

import androidx.room.*
import lijewski.demodb.data.source.entity.EmployeeEntity

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addEmployee(employee: EmployeeEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT) //TODO: check OnConflictStrategy.IGNORE
    suspend fun addEmployee(employeeList: List<EmployeeEntity>)

    @Delete
    suspend fun deleteEmployee(employee: EmployeeEntity)

    @Delete
    suspend fun deleteEmployee(employeeList: List<EmployeeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editEmployee(employee: EmployeeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editEmployee(employeeList: List<EmployeeEntity>)

    @Query("SELECT * from employees_table WHERE first_name = :firstName OR last_name = :lastName ORDER BY id")
    suspend fun getEmployeeByName(firstName: String, lastName: String): List<EmployeeEntity>

    @Query("SELECT * from employees_table ORDER BY id")
    suspend fun getAllEmployees(): List<EmployeeEntity>

}
