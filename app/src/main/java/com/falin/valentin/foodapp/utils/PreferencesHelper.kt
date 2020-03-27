package com.falin.valentin.foodapp.utils

import android.content.Context
import android.preference.PreferenceManager

/**
 * This class help to work with SharedPreferences.
 *
 * @param context Context of our application.
 * @property introInfo It is for storing information in SharedPreferences about viewing an IntroActivity.
 * @property productDisplayMode Used for display the Products adapter view mode
 */
class PreferencesHelper(context: Context) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var introInfo: Boolean
        get() = preferences.getBoolean(INTRO_INFO, false)
        set(value) = preferences.edit().putBoolean(INTRO_INFO, value).apply()

    var productDisplayMode: Int
        get() = preferences.getInt(PRODUCT_MODE, 0)
        set(value) = preferences.edit().putInt(PRODUCT_MODE, value).apply()

    var isUserAuthorized: Boolean
        get() = preferences.getBoolean(IS_USER_AUTH, false)
        set(value) = preferences.edit().putBoolean(IS_USER_AUTH, value).apply()

    companion object {
        private const val INTRO_INFO = "data.source.prefs.INTRO_INFO"
        private const val PRODUCT_MODE = "data.source.prefs.PRODUCT_MODE"
        private const val IS_USER_AUTH = "data.source.prefs.IS_USER_AUTH"
    }
}