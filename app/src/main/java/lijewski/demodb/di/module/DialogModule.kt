package lijewski.demodb.di.module

import dagger.Module
import dagger.Provides
import lijewski.demodb.presentation.main.DialogInterface
import lijewski.demodb.presentation.main.MainActivity

@Module
class DialogModule {
    @Provides
    fun bindDismissListener(mainActivity: MainActivity): DialogInterface {
        return mainActivity
    }
}
