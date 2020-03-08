package lijewski.demodb.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.main_activity.*
import lijewski.demodb.app.R
import lijewski.demodb.presentation.add.AddEmployeeDialogFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector, DialogInterface {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingAndroidInjector


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            val fragment = MainFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment,
                    MainFragment.TAG
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
}
