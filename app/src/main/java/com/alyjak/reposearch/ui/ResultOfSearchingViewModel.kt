package com.alyjak.reposearch.ui

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
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

    init {
        viewModelScope.launch {
            gitHubRepository.getSearchingResult()
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
