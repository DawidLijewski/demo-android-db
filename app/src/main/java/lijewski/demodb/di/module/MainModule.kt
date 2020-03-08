package lijewski.demodb.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lijewski.demodb.presentation.add.AddEmployeeDialogFragment
import lijewski.demodb.presentation.dashboard.DashboardFragment
import lijewski.demodb.presentation.search.SearchEmployeeFragment

@Suppress("unused")
@Module(includes = [DialogModule::class])
abstract class MainModule {
    @ContributesAndroidInjector(modules = [(DashboardModule::class)])
    abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun contributeAddEmployeeDialogFragment(): AddEmployeeDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchEmployeeFragment(): SearchEmployeeFragment
}
