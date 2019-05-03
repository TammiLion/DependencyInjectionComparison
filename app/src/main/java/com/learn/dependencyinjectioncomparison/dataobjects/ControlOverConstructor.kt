package com.learn.dependencyinjectioncomparison.dataobjects

import javax.inject.Inject
import javax.inject.Singleton

//dagger @Inject annotation in front of constructor
//kodein
//koin

class ControlOverConstructor @Inject constructor() {
    var count = 0

    init {
        count++
    }
}

@Singleton
class DaggerSingletonControlOverConstructor @Inject constructor() {
    var count = 0

    init {
        count++
    }
}