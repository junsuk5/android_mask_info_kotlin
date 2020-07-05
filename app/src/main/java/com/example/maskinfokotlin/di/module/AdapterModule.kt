package com.example.maskinfokotlin.di.module

import com.example.maskinfokotlin.StoreAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object AdapterModule {

    @Provides
    fun provideStoreAdapter(): StoreAdapter {
        return StoreAdapter()
    }
}