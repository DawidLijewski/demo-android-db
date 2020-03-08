package lijewski.demodb.presentation.add

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.add_employee_dialog.*
import lijewski.demodb.app.R
import lijewski.demodb.app.databinding.AddEmployeeDialogBinding
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.presentation.main.DialogInterface
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class AddEmployeeDialogFragment : DaggerDialogFragment() {
    companion object {
        const val TAG: String = "AddEmployeeDialog"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dialogInterface: DialogInterface

    private val maxDate: Long = getCalendarMaxDate()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_close.setOnClickListener {
            handleDismiss()
        }
        btn_save.setOnClickListener {
            if (addEmployeeViewModel.checkIsNewEmployeeCorrect()) {
                addEmployeeViewModel.addNewEmployee()
            } else {
                showInputErrorDialog()
            }
        }

        birthdate_date_picker.maxDate = maxDate
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        birthdate_date_picker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            addEmployeeViewModel.birthDate.value = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
        }

        addEmployeeViewModel.error.observe(viewLifecycleOwner, Observer {
            showSaveErrorDialog()
        })
        addEmployeeViewModel.eventAddSuccess.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                handleDismiss()
            }
        })
        addEmployeeViewModel.birthDate.value = milliEpochToLocalDate(maxDate)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    private fun showInputErrorDialog() {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.error)
                .setMessage(R.string.error_empty_field)
                .setPositiveButton(R.string.ok) { errorDialog, _ ->
                    errorDialog.dismiss()
                }
                .show()
        }
    }

    private fun showSaveErrorDialog() {
        Toast.makeText(context, R.string.error_saving_data, Toast.LENGTH_SHORT).show()
    }

    private fun getCalendarMaxDate(): Long {
        val maxLocalDate = LocalDate.now().minusYears(Employee.MAX_AGE)
        return localDateToMilliEpoch(maxLocalDate)
    }

    private fun milliEpochToLocalDate(milliEpoch: Long): LocalDate {
        return Instant.ofEpochMilli(milliEpoch).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    private fun localDateToMilliEpoch(localDate: LocalDate): Long {
        val zoneId: ZoneId = ZoneId.systemDefault()
        return localDate.atStartOfDay(zoneId).toInstant().toEpochMilli()
    }

    private fun handleDismiss() {
        dialogInterface.onCloseDialog()
        dismiss()
    }
}
