package lijewski.demodb.presentation.add

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.add_employee_dialog.*
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_close.setOnClickListener {
            dismiss()
        }
        btn_save.setOnClickListener {
            if (addEmployeeViewModel.checkIsNewEmployeeCorrect()) {
                addEmployeeViewModel.addNewEmployee()
            } else {
                showErrorDialog()
            }
        }
    }

    private fun showErrorDialog() {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.error)
                .setMessage(R.string.error_empty_field)
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}
