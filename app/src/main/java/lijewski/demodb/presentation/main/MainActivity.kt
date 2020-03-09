package lijewski.demodb.presentation.main

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*
import lijewski.demodb.app.R
import lijewski.demodb.presentation.add.AddEmployeeDialogFragment
import lijewski.demodb.presentation.dashboard.DashboardFragment

class MainActivity : DaggerAppCompatActivity(), MainDialogInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, DashboardFragment(),
                    DashboardFragment.TAG
                ).commit()
        }

        fab.setOnClickListener {
            openAddEmployeeDialog()
        }
    }

    private fun openAddEmployeeDialog() {
        fab.hide()

        val addEmployeeDialogFragment = AddEmployeeDialogFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(android.R.id.content, addEmployeeDialogFragment).addToBackStack(null).commit()
    }

    override fun onCloseDialog() {
        fab.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        fab.show()
    }
}
