package com.learn.dependencyinjectioncomparison.modules

import com.learn.dependencyinjectioncomparison.DicApp
import com.learn.dependencyinjectioncomparison.dataobjects.FakeWeDontHaveControlOverConstructor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BagWithSingletonDependency {
    @Provides
    @Singleton
    open fun providesFakeThingie(): FakeWeDontHaveControlOverConstructor {
        return FakeWeDontHaveControlOverConstructor.create()
    }
}

@Module
class ProviderBagOfDependencies {
    @Provides
    open fun providesFakeThingie(): FakeWeDontHaveControlOverConstructor {
        return FakeWeDontHaveControlOverConstructor.create()
    }
}

@Module
class BagWithAppDependencies(private val app: DicApp) {
    @Provides
    open fun providesApp(): DicApp {
        return app
    }
}