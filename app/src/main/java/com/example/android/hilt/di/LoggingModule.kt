package com.example.android.hilt.di

import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.data.LoggerInMemoryDataSource
import com.example.android.hilt.data.LoggerLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * author:       lans
 * date:         2022/7/8 09:37
 * description:   如果类型限定了作用域，@Binds 方法必须具有限定作用域的注解，因此，上面的函数带有 @Singleton 和 @ActivityScoped 注解。
 *                如果 @Binds 或 @Provides 用作某个类型的绑定，则该类型中限定作用域的注解将不会再使用，因此您可以开始将其从其他实现类中移除。
 **/


//重要提示：@DatabaseLogger 限定符安装在 ApplicationComponent 中，可注入到 LogApplication 类中。
//但是，由于 @InMemoryLogger 安装在 ActivityComponent 中，它无法注入到 LogApplication 类中，原因是 application 容器不知道该绑定。

@Qualifier
annotation class InMemoryLogger

@Qualifier
annotation class DatabaseLogger

@InstallIn(SingletonComponent::class)
@Module
abstract class LoggingDatabaseModule {
    @DatabaseLogger
    @Singleton
    @Binds
    abstract fun bindDatabaseLogger(iml: LoggerLocalDataSource): LoggerDataSource
}

@InstallIn(ActivityComponent::class)
@Module
abstract class LoggingInMemoryModule {
    @InMemoryLogger
    @ActivityScoped
    @Binds
    abstract fun bindInMemoryLogger(impl: LoggerInMemoryDataSource): LoggerDataSource
}
