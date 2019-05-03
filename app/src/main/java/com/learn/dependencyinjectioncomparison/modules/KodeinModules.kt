package com.learn.dependencyinjectioncomparison.modules

import com.learn.dependencyinjectioncomparison.dataobjects.ControlOverConstructor
import com.learn.dependencyinjectioncomparison.dataobjects.FakeWeDontHaveControlOverConstructor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/*
provider: always return a new implementation instance.
singleton: creates only on implementation instance, and always return that same instance.
 */

val kodeinSingletonBagOfDependencies = Kodein.Module("singleton") {
    bind<ControlOverConstructor>() with singleton { ControlOverConstructor() }
    bind<FakeWeDontHaveControlOverConstructor>() with singleton { FakeWeDontHaveControlOverConstructor.create() }
}

val kodeinProviderBagOfDependencies = Kodein.Module("provider", true) {
    bind<ControlOverConstructor>() with provider { ControlOverConstructor() }
    bind<FakeWeDontHaveControlOverConstructor>() with provider { FakeWeDontHaveControlOverConstructor.create() }
}