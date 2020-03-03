package lijewski.demodb.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lijewski.demodb.presentation.main.MainFragment

@Suppress("unused")
@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}
