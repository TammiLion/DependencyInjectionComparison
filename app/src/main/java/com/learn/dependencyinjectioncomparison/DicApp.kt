package com.learn.dependencyinjectioncomparison

import android.app.Application
import com.learn.dependencyinjectioncomparison.modules.BagWithAppDependencies
import com.learn.dependencyinjectioncomparison.modules.BagWithSingletonDependency
import com.learn.dependencyinjectioncomparison.modules.kodeinSingletonBagOfDependencies
import com.learn.dependencyinjectioncomparison.modules.koinSingletonBagOfDependencies
import dagger.Component
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import javax.inject.Singleton

/* dagger
It's very common to create a component in the Application class that contains a module which can provide this class as a dependency to others.
*/

/* kodein
Extending KodeinAware by the Application class. The kodein field with the Kodein.Lazy delegate.
*/

/* koin
 startKoin block
 */

class DicApp : Application(), KodeinAware {

    //BagWithAppDependencies wasn't necessary but I wanted to provide a similar example to what kodein and koin were giving (androidXModule and androidContext)
    //A component is necessary whenever you have a module, defining it in the application gives it a singleton scope (honestly, the @Singleton does nothing!)
    private val appComponent: ComponentDefinedInApplication =
        DaggerComponentDefinedInApplication.builder().bagWithAppDependencies(BagWithAppDependencies(this)).build()

    override val kodein by Kodein.lazy {
        import(androidXModule(this@DicApp)) //not sure if this is necessary yet, probs only for context di
        import(kodeinSingletonBagOfDependencies)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DicApp) //not sure if this is necessary yet, probs only for context di
            modules(koinSingletonBagOfDependencies)
        }
    }

    fun getAppComponent(): ComponentDefinedInApplication = appComponent
}

@Singleton
@Component(modules = [BagWithAppDependencies::class, BagWithSingletonDependency::class])
interface ComponentDefinedInApplication {
    fun giveMeMyDependencies(app: SingletonActivity)
}