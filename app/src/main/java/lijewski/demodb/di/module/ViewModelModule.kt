package lijewski.demodb.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import lijewski.demodb.di.factory.ViewModelFactory
import lijewski.demodb.di.key.ViewModelKey
import lijewski.demodb.presentation.add.AddEmployeeViewModel
import lijewski.demodb.presentation.dashboard.DashboardViewModel
import lijewski.demodb.presentation.search.SearchEmployeeViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(mainViewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddEmployeeViewModel::class)
    abstract fun bindAddEmployeeViewModel(addEmployeeViewModel: AddEmployeeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchEmployeeViewModel::class)
    abstract fun bindSearchEmployeeViewModel(searchEmployeeViewModel: SearchEmployeeViewModel):ViewModel
}
