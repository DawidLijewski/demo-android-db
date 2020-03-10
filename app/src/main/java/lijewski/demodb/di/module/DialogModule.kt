package lijewski.demodb.di.module

import dagger.Module
import dagger.Provides
import lijewski.demodb.presentation.main.MainActivity
import lijewski.demodb.presentation.main.MainDialogInterface

@Module
class DialogModule {
    @Provides
    fun bindDismissListener(mainActivity: MainActivity): MainDialogInterface {
        return mainActivity
    }
}
