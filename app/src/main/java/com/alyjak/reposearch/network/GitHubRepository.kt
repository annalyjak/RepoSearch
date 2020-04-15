package com.alyjak.reposearch.network

import androidx.lifecycle.MutableLiveData
import com.alyjak.reposearch.network.enums.Order
import com.alyjak.reposearch.network.enums.SortingStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubRepository {

    var results: MutableLiveData<Model.SearchingResult> = MutableLiveData()

    suspend fun getSearchingResult(query: String, sortingStrategy: SortingStrategy?, order: Order?) {
        withContext(Dispatchers.IO) {
            results.postValue(Network.gitHubService
                .getRepositories(q = query, sort = sortingStrategy?.sortType, order = order?.order)
                .await())
        }
    }

    suspend fun clearResult() {
        withContext(Dispatchers.Main) {
            results.value = null
        }
    }
}