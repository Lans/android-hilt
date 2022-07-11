package com.example.android.hilt.viewmodel

import android.util.Log
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * author:       lans
 * date:         2022/7/11 14:26
 * description:
 **/
@ViewModelScoped
class TestModel @Inject constructor() {
    fun printLog() {
        Log.e("TestModel", "TestModel被调用")
    }
}