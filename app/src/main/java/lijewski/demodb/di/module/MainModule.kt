package lijewski.demodb.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lijewski.demodb.presentation.add.AddEmployeeDialogFragment
import lijewski.demodb.presentation.main.MainFragment
import lijewski.demodb.presentation.search.SearchEmployeeFragment

@Suppress("unused")
@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeAddEmployeeDialogFragment(): AddEmployeeDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchEmployeeFragment(): SearchEmployeeFragment
}
