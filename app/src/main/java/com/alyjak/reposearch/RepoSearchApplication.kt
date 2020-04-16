package com.alyjak.reposearch

import android.app.Application
import com.alyjak.reposearch.di.repositoryModule
import com.alyjak.reposearch.di.retrofitModule
import com.alyjak.reposearch.di.serviceModule
import com.alyjak.reposearch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class RepoSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@RepoSearchApplication)
            modules(listOf(retrofitModule, serviceModule, repositoryModule, viewModelModule))
        }
    }

}