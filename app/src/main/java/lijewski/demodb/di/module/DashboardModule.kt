package lijewski.demodb.di.module

import dagger.Module
import dagger.Provides
import lijewski.demodb.presentation.adapter.EmployeeListAdapter
import lijewski.demodb.presentation.dashboard.DashboardFragment

@Module
class DashboardModule {
    @Provides
    fun bindEmployeeListAdapter(onItemClickedListener: EmployeeListAdapter.OnItemClickedListener): EmployeeListAdapter {
        return EmployeeListAdapter(onItemClickedListener)
    }

    @Provides
    fun bindClickListener(dashboardFragment: DashboardFragment): EmployeeListAdapter.OnItemClickedListener {
        return dashboardFragment
    }
}
