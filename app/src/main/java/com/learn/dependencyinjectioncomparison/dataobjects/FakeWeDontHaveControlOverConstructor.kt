package com.learn.dependencyinjectioncomparison.dataobjects

class FakeWeDontHaveControlOverConstructor private constructor() {
    var count = 0

    init {
        count++
    }

    companion object {
        fun create(): FakeWeDontHaveControlOverConstructor = FakeWeDontHaveControlOverConstructor()
    }
}