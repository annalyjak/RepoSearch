package com.alyjak.reposearch.di

import com.alyjak.reposearch.network.GitHubRepository
import com.alyjak.reposearch.network.GitHubService
import com.alyjak.reposearch.ui.result.ResultOfSearchingViewModel
import com.alyjak.reposearch.ui.search.SearchViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val viewModelModule = module {
    viewModel {
        ResultOfSearchingViewModel(get())
    }
    viewModel {
        SearchViewModel()
    }
}

val repositoryModule = module {
    single {
        GitHubRepository(get())
    }
}

val serviceModule = module {
    fun provideGitHubService(retrofit: Retrofit): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }

    single { provideGitHubService(get()) }
}

val retrofitModule = module {

    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    fun provideRetrofit(factory: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single { provideMoshi() }
    single { provideRetrofit(get()) }
}