package lijewski.demodb.di.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import lijewski.demodb.App
import lijewski.demodb.di.module.AppModule
import lijewski.demodb.di.module.BuilderModule
import lijewski.demodb.di.module.DatabaseModule
import lijewski.demodb.di.module.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BuilderModule::class,
        DatabaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
