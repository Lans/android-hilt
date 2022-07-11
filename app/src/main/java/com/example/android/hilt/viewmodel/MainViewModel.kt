package com.example.android.hilt.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * author:       lans
 * date:         2022/7/11 14:26
 * description:
 **/
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var testModel: TestModel

    fun check() {
        testModel.printLog()
    }
}