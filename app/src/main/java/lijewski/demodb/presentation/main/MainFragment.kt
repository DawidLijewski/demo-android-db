package lijewski.demodb.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*
import lijewski.demodb.app.R
import lijewski.demodb.presentation.add.AddEmployeeDialogFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {
    companion object {
        const val TAG: String = "MainFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(mainViewModel) {
            isLoading.observe(viewLifecycleOwner, Observer {
                it?.let {
                    main_progressbar.visibility = if (it) View.VISIBLE else View.GONE
                }
            })

            //TODO: load employees into recyclerview
        }

        fab.setOnClickListener {
            openAddEmployeeDialog()
        }
    }

    private fun openAddEmployeeDialog() {
        val addEmployeeDialogFragment = AddEmployeeDialogFragment()
        addEmployeeDialogFragment.setTargetFragment(this, 0)
        parentFragmentManager.let { addEmployeeDialogFragment.show(it, AddEmployeeDialogFragment.TAG) }
    }
}
