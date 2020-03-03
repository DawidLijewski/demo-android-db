package lijewski.demodb.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import lijewski.demodb.data.source.db.AppDatabase
import lijewski.demodb.data.source.db.EmployeeDao
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideEmployeeDao(appDataBase: AppDatabase): EmployeeDao {
        return appDataBase.employeeDao()
    }
}
