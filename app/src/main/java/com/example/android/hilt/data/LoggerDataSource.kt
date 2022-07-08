package com.example.android.hilt.data

/**
 * author:       lans
 * date:         2022/7/8 09:31
 * description:
 **/
interface LoggerDataSource {
    fun addLog(msg: String)
    fun getAllLogs(callback: (List<Log>) -> Unit)
    fun removeLogs()
}