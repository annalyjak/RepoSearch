package com.alyjak.reposearch.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alyjak.reposearch.events.MakeSearchEvent
import com.alyjak.reposearch.network.GitHubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ResultOfSearchingViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val gitHubRepository = GitHubRepository()

    val resultOfSearching = gitHubRepository.results

    val numerOfResults = Transformations.map(resultOfSearching) {
        resultOfSearching.value?.totalCount.toString()
    }

    val results = Transformations.map(resultOfSearching) {
        resultOfSearching.value?.items
    }

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

    private val _internetConnection = MutableLiveData<Boolean>()
    val internetConnection: LiveData<Boolean>
        get() = _internetConnection

    init {
        _showProgressBar.value = false
        _internetConnection.value = false
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun searchResult(event: MakeSearchEvent) {
        _internetConnection.value = false
        viewModelScope.launch {
            _showProgressBar.value = true
            gitHubRepository.clearResult()
            gitHubRepository.getSearchingResult(event.query, event.sortingStrategy, event.order)
            _showProgressBar.value = false
        }
    }

    fun setInternetError() {
        _internetConnection.value = true
    }

}
