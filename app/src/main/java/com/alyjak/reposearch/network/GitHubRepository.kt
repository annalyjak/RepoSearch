package com.alyjak.reposearch.network

import androidx.lifecycle.MutableLiveData
import com.alyjak.reposearch.events.InternetConnectionErrorEvent
import com.alyjak.reposearch.network.enums.Order
import com.alyjak.reposearch.network.enums.SortingStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus

class GitHubRepository {

    var results: MutableLiveData<Model.SearchingResult> = MutableLiveData()

    suspend fun getSearchingResult(query: String, sortingStrategy: SortingStrategy?, order: Order?) {
        try {
            withContext(Dispatchers.IO) {
                results.postValue(Network.gitHubService
                    .getRepositoriesAsync(q = query, sort = sortingStrategy?.sortType, order = order?.order)
                    .await())
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                EventBus.getDefault().post(InternetConnectionErrorEvent(e))
            }
        }

    }

    suspend fun clearResult() {
        withContext(Dispatchers.Main) {
            results.value = null
        }
    }
}