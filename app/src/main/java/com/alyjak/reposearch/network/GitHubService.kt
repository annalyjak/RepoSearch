package com.alyjak.reposearch.network

import com.alyjak.reposearch.network.enums.Order
import com.alyjak.reposearch.network.enums.SortingStrategy
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("repositories")
    fun getRepositoriesAsync(
        @Query("q") q: String,
        @Query("sort") sort: String? = SortingStrategy.STARS.sortType,
        @Query("order") order: String? = Order.DESC.order
    ): Deferred<Model.SearchingResult>

}

