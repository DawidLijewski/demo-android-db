package lijewski.demodb.di.module

import dagger.Binds
import dagger.Module
import lijewski.demodb.data.repository.EmployeeRepositoryImpl
import lijewski.demodb.domain.repository.EmployeeRepository

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindsEmployeeRepository(employeeRepositoryImpl: EmployeeRepositoryImpl): EmployeeRepository
}
