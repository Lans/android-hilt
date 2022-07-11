package com.example.android.hilt.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

/**
 * author:       lans
 * date:         2022/7/8 10:53
 * description:
 **/
@InstallIn(ServiceComponent::class)
@Module
class ServiceModule {
    @Provides
    fun newService(): String {
        return "ServiceModule"
    }
}