package com.alyjak.reposearch.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("repositories")
    fun getRepositories(
        @Query("q") q: String,
        @Query("sort") sort: String? = SortingStrategy.STARS.sortType,
        @Query("order") order: String? = Order.DESC.order
    ): Deferred<Model.SearchingResult>

}

enum class SortingStrategy(val sortType: String) {
    STARS("stars"),
    FORKS("forks"),
    HELP_WANTED_ISSUES("help-wanted-issues"),
    UPDATED("updated")
}

enum class Order(val order: String) {
    DESC("desc"),
    ASC("asc")
}

