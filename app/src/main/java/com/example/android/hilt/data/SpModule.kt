package com.example.android.hilt.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * author:       lans
 * date:         2022/7/11 09:40
 * description:
 **/
@InstallIn(SingletonComponent::class)
@Module
class SpModule {
    @Provides
    @Singleton
    fun proSp(@ApplicationContext context: Context): SpUtils {
        return SpUtils(context)
    }
}