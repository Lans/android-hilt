package com.example.android.hilt.di

import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * author:       lans
 * date:         2022/7/7 11:01
 * description:
 **/

//Hilt 模块不能同时包含非静态和抽象绑定方法，因此您不能将 @Binds 和 @Provides 注解放在同一个类中
@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {
    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator
}