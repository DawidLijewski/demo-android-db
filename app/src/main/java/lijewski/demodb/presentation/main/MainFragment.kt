package lijewski.demodb.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import lijewski.demodb.app.R
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        with(mainViewModel) {
            isLoading.observe(viewLifecycleOwner, Observer {
                it?.let {
                    main_progressbar.visibility = if (it) View.VISIBLE else View.GONE
                }
            })

            //TODO: load employees into recyclerview
        }
    }
}
