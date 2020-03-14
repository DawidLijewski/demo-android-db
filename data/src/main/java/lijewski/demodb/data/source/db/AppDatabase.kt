package lijewski.demodb.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import lijewski.demodb.data.source.converter.AddressListTypeConverter
import lijewski.demodb.data.source.entity.EmployeeEntity

@Database(entities = [EmployeeEntity::class], version = AppDatabase.VERSION)
@TypeConverters(AddressListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "app.db"
        const val VERSION = 1
    }
    abstract fun employeeDao(): EmployeeDao
}