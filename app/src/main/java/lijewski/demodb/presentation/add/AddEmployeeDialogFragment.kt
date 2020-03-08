package lijewski.demodb.presentation.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerDialogFragment
import lijewski.demodb.app.R
import lijewski.demodb.app.databinding.AddEmployeeDialogBinding
import javax.inject.Inject

class AddEmployeeDialogFragment : DaggerDialogFragment() {
    companion object {
        const val TAG: String = "AddEmployeeDialog"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var addEmployeeViewModel: AddEmployeeViewModel

    private lateinit var binding: AddEmployeeDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addEmployeeViewModel =
            ViewModelProvider(this, viewModelFactory).get(AddEmployeeViewModel::class.java)

        binding = DataBindingUtil.inflate<AddEmployeeDialogBinding>(
            inflater, R.layout.add_employee_dialog, container, false
        ).apply {
            viewModel = addEmployeeViewModel
            lifecycleOwner = this@AddEmployeeDialogFragment
        }

        return binding.root
    }

}
