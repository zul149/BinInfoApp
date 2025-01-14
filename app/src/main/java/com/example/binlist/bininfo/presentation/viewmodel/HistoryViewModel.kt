package com.example.binlist.bininfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.bininfo.data.BinHistory
import com.example.binlist.bininfo.domain.DeleteHistoryUseCase
import com.example.binlist.bininfo.domain.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase,
    private val deleteHistoryUseCase: DeleteHistoryUseCase
) : ViewModel() {

    private val _history = MutableStateFlow<List<BinHistory>>(emptyList())
    val history: StateFlow<List<BinHistory>> = _history

    init {
        getHistory()
    }

    private fun getHistory() {
        viewModelScope.launch {
            val historyList = getHistoryUseCase()
            _history.value = historyList
        }
    }

    fun deleteHistoryItem(binHistory: BinHistory) {
        viewModelScope.launch {
            // Удаляем элемент из Room
            deleteHistoryUseCase(binHistory)
            // Обновляем список истории
            getHistory()
        }
    }
}