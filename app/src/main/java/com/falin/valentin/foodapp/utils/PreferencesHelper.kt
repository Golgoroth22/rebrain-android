package com.falin.valentin.foodapp.utils

import android.content.Context
import android.preference.PreferenceManager

/**
 * This class help to work with SharedPreferences.
 *
 * @param context Context of our application.
 * @property introInfo It is for storing information in SharedPreferences about viewing an IntroActivity.
 */
class PreferencesHelper(context: Context) {
    companion object {
        private const val INTRO_INFO = "data.source.prefs.INTRO_INFO"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var introInfo: Boolean
        get() = preferences.getBoolean(INTRO_INFO, false)
        set(value) = preferences.edit().putBoolean(INTRO_INFO, value).apply()
}