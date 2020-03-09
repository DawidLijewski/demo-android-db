package lijewski.demodb.presentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.dashboard_fragment.*
import lijewski.demodb.app.R
import lijewski.demodb.app.databinding.DashboardFragmentBinding
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.presentation.adapter.EmployeeListAdapter
import timber.log.Timber
import javax.inject.Inject

class DashboardFragment : DaggerFragment(), EmployeeListAdapter.OnItemClickedListener {
    companion object {
        const val TAG: String = "DashboardFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: EmployeeListAdapter

    private lateinit var binding: DashboardFragmentBinding

    private val dashboardViewModel: DashboardViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<DashboardFragmentBinding>(
            inflater, R.layout.dashboard_fragment, container, false
        ).apply {
            viewModel = dashboardViewModel
            lifecycleOwner = this@DashboardFragment
            recyclerView.adapter = adapter
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(dashboardViewModel) {
            isLoading.observe(viewLifecycleOwner, Observer {
                it?.let {
                    main_progressbar.visibility = if (it) View.VISIBLE else View.GONE
                }
            })

            employeeList.observe(viewLifecycleOwner, Observer {
                adapter.updateEmployeeList(it)
                binding.recyclerView.smoothScrollToPosition(0)
            })
            error.observe(viewLifecycleOwner, Observer {
                Timber.e(it)
                showFetchErrorToast()
            })
        }
    }

    override fun onItemClicked(employee: Employee, position: Int) {
        Timber.d(
            "Clicked employee: %s %s, position nr: %d",
            employee.lastName,
            employee.firstName,
            position
        )
    }

    private fun showFetchErrorToast() {
        Toast.makeText(context, R.string.error_saving_data, Toast.LENGTH_SHORT).show()
    }
}
