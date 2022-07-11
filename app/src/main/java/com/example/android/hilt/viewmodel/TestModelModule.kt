package com.example.android.hilt.viewmodel

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * author:       lans
 * date:         2022/7/11 14:28
 * description:
 **/
@InstallIn(ViewModelComponent::class)
@Module
class TestModelModule {
    @Provides
    fun createTestModelData(): TestModel {
        return TestModel()
    }
}