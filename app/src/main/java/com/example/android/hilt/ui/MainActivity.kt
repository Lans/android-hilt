/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.hilt.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.android.hilt.R
import com.example.android.hilt.data.SpUtils
import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.Screens
import com.example.android.hilt.service.NewServices
import com.example.android.hilt.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main activity of the application.
 *
 * Container for the Buttons & Logs fragments. This activity simply tracks clicks on buttons.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: AppNavigator

    @Inject
    lateinit var spUtils: SpUtils

//    老方法
//    @Inject
//    lateinit var mainViewModel: MainViewModel

    //    implementation "androidx.activity:activity-ktx:1.4.0"
    //    implementation "androidx.fragment:fragment-ktx:1.4.1"
    //    提供
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navigator = (applicationContext as LogApplication).serviceLocator.provideNavigator(this)

        if (savedInstanceState == null) {
            navigator.navigateTo(Screens.BUTTONS)
        }

        startForegroundService(Intent(this, NewServices::class.java))

        spUtils.put("main", this.javaClass.name)

        viewModel.check()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}
