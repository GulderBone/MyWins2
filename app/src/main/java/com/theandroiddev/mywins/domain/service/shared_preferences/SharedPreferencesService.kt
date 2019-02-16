package com.theandroiddev.mywins.domain.service.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.theandroiddev.mywins.core.injection.ApplicationContext
import com.theandroiddev.mywins.utils.Constants.Companion.PACKAGE_NAME
import com.theandroiddev.mywins.utils.SearchFilter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by jakub on 04.11.17.
 */

@Singleton
class SharedPreferencesService @Inject constructor(
    @ApplicationContext context: Context,
    private val gson: Gson
) {

    private val pref: SharedPreferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)

    //TODO handle first launch pref that have to save defaults to database...

    val isFirstSuccessAdded: Boolean
        get() = pref.getBoolean("firstsuccess", false)

    val isFirstRun: Boolean
        get() = pref.getBoolean("firstrun", true)

    fun clear() {
        pref.edit().clear().apply()
    }

    fun setFirstSuccessAdded() {
        pref.edit().putBoolean("firstsuccess", true).apply()
    }

    fun setNotFirstRun() {
        pref.edit().putBoolean("firstrun", false).apply()
    }

    fun getSuccessesFilters(): SearchFilter {
        val json = pref.getString("successes_filters", gson.toJson(SearchFilter()))
        return gson.fromJson(json, SearchFilter::class.java)
    }

    fun saveSuccessesFilters(newFilters: SearchFilter) {
        pref.edit().putString("successes_filters", gson.toJson(newFilters)).apply()
    }
}
