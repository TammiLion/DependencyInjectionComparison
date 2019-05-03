package com.learn.dependencyinjectioncomparison

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.learn.dependencyinjectioncomparison.dataobjects.ControlOverConstructor
import com.learn.dependencyinjectioncomparison.dataobjects.DaggerSingletonControlOverConstructor
import com.learn.dependencyinjectioncomparison.dataobjects.FakeWeDontHaveControlOverConstructor
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import org.koin.android.ext.android.inject
import javax.inject.Inject

/* dagger
Create a component (doesn't have to be in the activity, can also be defined in the application).
Call the method that handles DI (I named it giveMeMyDependencies, mostly people call it inject).
 */

/* kodein
Extend KodeinAware. Define the overriden kodein field. Delegate with instance.
 */

/* koin
Delegate with inject.
 */
class SingletonActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    @Inject
    lateinit var daggerControlled: DaggerSingletonControlOverConstructor
    @Inject
    lateinit
    var daggerNoControl: FakeWeDontHaveControlOverConstructor

    private val kodeinControlled: ControlOverConstructor by instance()
    private val kodeinNoControl: FakeWeDontHaveControlOverConstructor by instance()

    private val koinControlled: ControlOverConstructor by inject()
    private val koinNoControl: FakeWeDontHaveControlOverConstructor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Singleton Example"
        gotoSingletonActivity.visibility = View.VISIBLE
        (application as DicApp).getAppComponent().giveMeMyDependencies(this)
        setClickListeners()
    }

    private fun setClickListeners() {
        daggerButton.setOnClickListener { onClickTestDagger() }
        kodeinButton.setOnClickListener { onClickTestKodein() }
        koinButton.setOnClickListener { onClickTestKoin() }
        gotoSingletonActivity.setOnClickListener { startActivity(Intent(this, ProviderActivity::class.java)) }
    }

    private fun onClickTestDagger() {
        daggerControlled.count++
        daggerText.text = daggerControlled.count.toString()

        daggerNoControl.count++
        daggerText2.text = daggerNoControl.count.toString()
    }

    private fun onClickTestKodein() {
        kodeinControlled.count++
        kodeinText.text = kodeinControlled.count.toString()

        kodeinNoControl.count++
        kodeinText2.text = kodeinNoControl.count.toString()
    }

    private fun onClickTestKoin() {
        koinControlled.count++
        koinText.text = koinControlled.count.toString()

        koinNoControl.count++
        koinText2.text = koinNoControl.count.toString()
    }
}
