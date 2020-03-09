package lijewski.demodb.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import lijewski.demodb.app.R
import lijewski.demodb.app.databinding.SearchFragmentBinding
import lijewski.demodb.presentation.dashboard.DashboardViewModel
import timber.log.Timber
import javax.inject.Inject

class SearchFragment : DaggerFragment() {
    companion object {
        const val TAG: String = "SearchFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: SearchFragmentBinding

    private val dashboardViewModel: DashboardViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
    }

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<SearchFragmentBinding>(
            inflater, R.layout.search_fragment, container, false
        ).apply {
            viewModel = searchViewModel
            lifecycleOwner = this@SearchFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(searchViewModel) {
            employeeList.observe(viewLifecycleOwner, Observer {
                Timber.d("Search query successful, size: %s", it.size)
                dashboardViewModel.employeeList.value = it
            })

            error.observe(viewLifecycleOwner, Observer {
                Timber.e(it)
                showSearchErrorToast()
            })
        }
    }

    private fun showSearchErrorToast() {
        Toast.makeText(context, R.string.error_search_data, Toast.LENGTH_SHORT).show()
    }
}
