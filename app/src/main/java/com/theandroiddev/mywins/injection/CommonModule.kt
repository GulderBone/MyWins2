package com.theandroiddev.mywins.injection

import com.theandroiddev.mywins.utils.SuccessesConfig
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides
    fun contributeSuccessConfig() = SuccessesConfig()
}