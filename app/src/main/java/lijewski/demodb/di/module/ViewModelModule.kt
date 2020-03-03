package lijewski.demodb.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import lijewski.demodb.di.factory.ViewModelFactory
import lijewski.demodb.di.key.ViewModelKey
import lijewski.demodb.presentation.main.MainViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: MainViewModel): ViewModel
}
