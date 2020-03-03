package lijewski.demodb.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lijewski.demodb.presentation.main.MainActivity

@Suppress("unused")
@Module
abstract class BuilderModule {
    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMainActivity(): MainActivity
}
