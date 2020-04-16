package com.alyjak.reposearch

import android.app.Application
import timber.log.Timber

class RepoSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}