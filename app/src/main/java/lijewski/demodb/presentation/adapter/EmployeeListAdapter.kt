package lijewski.demodb.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lijewski.demodb.app.R
import lijewski.demodb.app.databinding.ItemEmployeeBinding
import lijewski.demodb.domain.model.Employee
import java.util.*

class EmployeeListAdapter(private val onItemClickedListener: OnItemClickedListener) :
    RecyclerView.Adapter<EmployeeListAdapter.EmployeeItemViewHolder>() {
    private val employeeList = ArrayList<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemEmployeeBinding>(
            layoutInflater,
            R.layout.item_employee,
            parent,
            false
        )
        return EmployeeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeItemViewHolder, position: Int) {
        val employee = employeeList[position]
        holder.bind(employee)

        holder.itemView.setOnClickListener {
            onItemClickedListener.onItemClicked(employee, position)
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    fun updateEmployeeList(newEmployeeList: List<Employee>?) {
        this.employeeList.clear()
        if (newEmployeeList == null || newEmployeeList.isEmpty()) {
            notifyDataSetChanged()
        } else {
            this.employeeList.addAll(0, newEmployeeList)
            notifyItemInserted(0)
        }
    }

    inner class EmployeeItemViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: Employee) {
            binding.employeeObject = employee
        }
    }

    /**
     * Listener for item click interactions.
     */
    interface OnItemClickedListener {
        fun onItemClicked(employee: Employee, position: Int)
    }
}
