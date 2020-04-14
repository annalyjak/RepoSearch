package com.alyjak.reposearch.network

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubRepository {

    var results: MutableLiveData<Model.SearchingResult> = MutableLiveData()

    suspend fun getSearchingResult() {
        withContext(Dispatchers.IO) {
            results.postValue(Network.gitHubService
                .getRepositories("tetris")
                .await())
        }
    }
}