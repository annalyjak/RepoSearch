package com.alyjak.reposearch.ui.search

import android.text.Editable
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alyjak.reposearch.events.MakeSearchEvent
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
            EventBus.getDefault().post(MakeSearchEvent(query))
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
