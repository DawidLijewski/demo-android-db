package lijewski.demodb.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.main_activity.*
import lijewski.demodb.app.R
import lijewski.demodb.presentation.add.AddEmployeeDialogFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
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
        val addEmployeeDialogFragment = AddEmployeeDialogFragment()
        supportFragmentManager.let { addEmployeeDialogFragment.show(it, AddEmployeeDialogFragment.TAG) }
    }

}
