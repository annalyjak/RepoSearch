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
        @field:Json(name = "id") val id: String?,
        @field:Json(name = "name") var name: String?
    )

}