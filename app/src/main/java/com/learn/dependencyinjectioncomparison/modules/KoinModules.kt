package com.learn.dependencyinjectioncomparison.modules

import com.learn.dependencyinjectioncomparison.dataobjects.ControlOverConstructor
import com.learn.dependencyinjectioncomparison.dataobjects.FakeWeDontHaveControlOverConstructor
import org.koin.dsl.module

/*
single: use the same instance every time
factory: create a new instance each time
 */

val koinSingletonBagOfDependencies = module {
    single { FakeWeDontHaveControlOverConstructor.create() }
    single { ControlOverConstructor() }
}

val koinFactoryBagOfDependencies = module(override = true) {
    factory { FakeWeDontHaveControlOverConstructor.create() }
    factory { ControlOverConstructor() }
}