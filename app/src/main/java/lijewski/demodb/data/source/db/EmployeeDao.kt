package lijewski.demodb.data.source.db

import androidx.room.*
import lijewski.demodb.data.source.entity.EmployeeEntity

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: EmployeeEntity): Long

    @Delete
    suspend fun deleteEmployee(employee: EmployeeEntity): Int

    @Query("SELECT * from employees ORDER BY id")
    suspend fun selectAllEmployees(): List<EmployeeEntity>

}
