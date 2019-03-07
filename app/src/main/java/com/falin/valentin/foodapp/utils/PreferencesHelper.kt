package com.falin.valentin.foodapp.utils

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(context: Context) {
    companion object {
        private const val INTRO_INFO = "data.source.prefs.INTRO_INFO"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var introInfo = preferences.getBoolean(INTRO_INFO, false)
        set(value) = preferences.edit().putBoolean(INTRO_INFO, value).apply()
}