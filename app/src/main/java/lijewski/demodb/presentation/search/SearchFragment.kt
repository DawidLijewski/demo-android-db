package lijewski.demodb.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import lijewski.demodb.app.R
import lijewski.demodb.presentation.dashboard.DashboardViewModel
import timber.log.Timber
import javax.inject.Inject

class SearchFragment : DaggerFragment() {
    companion object {
        const val TAG: String = "SearchFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val dashboardViewModel: DashboardViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
    }

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(searchViewModel) {
            employeeList.observe(viewLifecycleOwner, Observer {
                if(it?.isNotEmpty() == true) {
                    dashboardViewModel.employeeList.value = it
                }
            })

            error.observe(viewLifecycleOwner, Observer {
                Timber.e(it)
                showSearchErrorToast()
            })
        }
        with(dashboardViewModel) {
            error.observe(viewLifecycleOwner, Observer {
                Timber.e(it)
            })
        }
    }

    private fun showSearchErrorToast() {
        Toast.makeText(context, R.string.error_search_data, Toast.LENGTH_SHORT).show()
    }
}
