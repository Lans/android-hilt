package com.example.android.hilt.data

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author:       lans
 * date:         2022/7/11 09:30
 * description:
 **/
@Singleton
class SpUtils @Inject constructor(context: Context) {
    private var spUtils: SharedPreferences

    companion object {
        const val NAME = "sp"
    }

    init {
        spUtils = context.getSharedPreferences(NAME, 0)
    }

    fun put(tag: String, str: Any) {
        val edit = spUtils.edit()
        when (str) {
            is Boolean -> edit.putBoolean(tag, str)
            is String -> edit.putString(tag, str)
            is Int -> edit.putInt(tag, str)
            is Float -> edit.putFloat(tag, str)
        }
        edit.apply()
    }

    fun get(tag: String): String? {
        return spUtils.getString(tag,"")
    }
}