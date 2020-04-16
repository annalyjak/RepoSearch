package com.alyjak.reposearch.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

object Model {

    @JsonClass(generateAdapter = true)
    data class SearchingResult(
        @Json(name = "total_count") @field:Json(name = "total_count") var totalCount: Int?,
        @Json(name = "incomplete_results") @field:Json(name = "incomplete_results") var incompleteResults: Boolean?,
        @field:Json(name = "items") var items: List<RepositoryResultInfo>?
    )

    @JsonClass(generateAdapter = true)
    class RepositoryResultInfo(
        @Json(name = "id") @field:Json(name = "id") val id: String?,
        @Json(name = "name") @field:Json(name = "name") var name: String?,
        @Json(name = "full_name") @field:Json(name = "full_name") val fullName: String?,
        @Json(name = "description") @field:Json(name = "description") val description: String?,
        @Json(name = "language") @field:Json(name = "language") val language: String?,
        @Json(name = "stargazers_count") @field:Json(name = "stargazers_count") val stargazersCount: Int?,
        @Json(name = "forks_count") @field:Json(name = "forks_count") val forksCount: Int?,
        @Json(name = "watchers_count") @field:Json(name = "watchers_count") val watchersCount: Int?,
        @Json(name = "ssh_url") @field:Json(name = "ssh_url") val sshUrl: String?,
        @Json(name = "clone_url") @field:Json(name = "clone_url") val cloneUrl: String?,
        @Json(name = "license") @field:Json(name = "license") val license: License?,
        @Json(name = "owner") @field:Json(name = "owner") val owner: Owner?
    )

    @JsonClass(generateAdapter = true)
    class License(
        @field:Json(name = "key") val key: String?,
        @field:Json(name = "name") val name: String?
    )

    @JsonClass(generateAdapter = true)
    class Owner(
        @field:Json(name = "id") val id: String?,
        @field:Json(name = "login") val login: String?,
        @Json(name = "avatar_url") @field:Json(name = "avatar_url") val avatarUrl: String?,
        @Json(name = "html_url") @field:Json(name = "html_url") val htmlUrl: String?
    )

}