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

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.android.hilt.R
import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.data.LoggerLocalDataSource
import com.example.android.hilt.data.SpUtils
import com.example.android.hilt.di.DatabaseLogger
import com.example.android.hilt.di.InMemoryLogger
import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.Screens
import com.example.android.hilt.viewmodel.MainIntent
import com.example.android.hilt.viewmodel.MainState
import com.example.android.hilt.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Fragment that displays buttons whose interactions are recorded.
 */
@AndroidEntryPoint
class ButtonsFragment : Fragment() {

    @DatabaseLogger
    @Inject
    lateinit var logger: LoggerDataSource

    @Inject
    lateinit var navigator: AppNavigator

    val viewModel by viewModels<MainViewModel>()


    @Inject
    lateinit var spUtils: SpUtils
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buttons, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                when (it) {
                    is MainState.BeforeShow -> {
                        logger.addLog("Interaction with 'BeforeShow'")
                    }
                    is MainState.Show -> {
                        logger.addLog("Interaction with 'Show'")

                    }
                    is MainState.Dismiss -> {
                        logger.addLog("Interaction with 'Dismiss'")

                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

//        populateFields(context)
    }

    private fun populateFields(context: Context) {
//        logger = (context.applicationContext as LogApplication).
//            serviceLocator.loggerLocalDataSource
//
//        navigator = (context.applicationContext as LogApplication).
//            serviceLocator.provideNavigator(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.button1).setOnClickListener {
//            logger.addLog("Interaction with 'MainIntent showLog'")
            doLaunch {
                viewModel.intent.send(MainIntent.ShowLog(1))
            }
//            viewModel.show()
        }

        view.findViewById<Button>(R.id.button2).setOnClickListener {
//            logger.addLog("Interaction with 'MainIntent dismissLog'")
            doLaunch {
                viewModel.intent.send(MainIntent.DismissLog)
            }
//            viewModel.dismiss()
        }

        view.findViewById<Button>(R.id.button3).setOnClickListener {
            logger.addLog("Interaction with 'Button 3'")
        }

        view.findViewById<Button>(R.id.all_logs).setOnClickListener {
//            spUtils.get("main")?.let { it1 -> logger.addLog(it1) }
            navigator.navigateTo(Screens.LOGS)
        }

        view.findViewById<Button>(R.id.delete_logs).setOnClickListener {
            logger.removeLogs()
        }
    }

    fun doLaunch(block: suspend CoroutineScope.() -> Unit) {
        GlobalScope.launch {
            block.invoke(this)
        }
    }
}
