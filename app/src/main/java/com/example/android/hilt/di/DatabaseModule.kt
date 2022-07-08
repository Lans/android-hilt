package com.example.android.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import com.example.android.hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * author:       lans
 * date:         2022/7/7 10:48
 * description:
 **/
@InstallIn(SingletonComponent::class)// 会通过指定 Hilt 组件告知 Hilt 绑定在哪些容器中可用
@Module //会告知 Hilt 这是一个模块
class DatabaseModule {

    //告知 Hilt 如何提供无法通过构造函数注入的类型。
    @Provides
    fun provideLogDao(database: AppDatabase): LogDao {
        return database.logDao()
    }

    //每个 Hilt 容器都随附一组默认绑定，可作为依赖项注入到您的自定义绑定。
    //applicationContext 便是这样：要访问它，您需要为字段添加 @ApplicationContext 注解
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "logging.db"
        ).build()
    }
}