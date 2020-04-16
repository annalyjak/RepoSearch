package com.alyjak.reposearch.ui.search

import android.text.Editable
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alyjak.reposearch.events.MakeSearchEvent
import com.alyjak.reposearch.network.enums.Order
import com.alyjak.reposearch.network.enums.SortingStrategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.greenrobot.eventbus.EventBus

class SearchViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _showAdvancedOptions = MutableLiveData<Boolean>()
    private var _expandIcon = MutableLiveData<Int>()

    val expandIcon: LiveData<Int>
        get() = _expandIcon

    val showAdvancedOptions: LiveData<Boolean>
        get() = _showAdvancedOptions

    val strategyEntries = arrayListOf(
        SortingStrategy.STARS,
        SortingStrategy.FORKS,
        SortingStrategy.HELP_WANTED_ISSUES,
        SortingStrategy.UPDATED
    )
    var selectedStrategy: Int = 0

    val orderEntries = arrayListOf(
        Order.DESC,
        Order.ASC
    )
    var selectedOrder: Int = 0

    val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean>
        get() = _showError


    init {
        _showAdvancedOptions.value = false
        _expandIcon.value = View.GONE
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onButtonSearchClick(text: Editable?) {
        val query = text.toString()
        if (query.isNotEmpty()) {
            removeInputTextError()
            if (_showAdvancedOptions.value != null && _showAdvancedOptions.value!!) {
                EventBus.getDefault().post(
                    MakeSearchEvent(query, strategyEntries[selectedStrategy], orderEntries[selectedOrder])
                )
            } else {
                EventBus.getDefault().post(
                    MakeSearchEvent(query)
                )
            }
        } else {
            setInputTextError()
        }
    }

    private fun setInputTextError() {
        _showError.value = true
    }

    private fun removeInputTextError() {
        _showError.value = false
    }

    fun onTextChange(text: CharSequence) {
        if (_showError.value != null && _showError.value!! && text.isNotEmpty()) {
            removeInputTextError()
        }
    }

    fun expandMoreOrLess() {
        when (_showAdvancedOptions.value) {
            true -> {
                _showAdvancedOptions.value = false
                _expandIcon.value = View.GONE
            }
            else -> {
                _showAdvancedOptions.value = true
                _expandIcon.value = View.VISIBLE
            }
        }
    }

}
