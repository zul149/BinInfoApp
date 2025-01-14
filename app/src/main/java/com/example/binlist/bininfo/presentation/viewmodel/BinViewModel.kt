package com.example.binlist.bininfo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.bininfo.data.BinHistory
import com.example.binlist.bininfo.data.BinInfo
import com.example.binlist.bininfo.data.BinRepository
import com.example.binlist.bininfo.domain.GetBinInfoUseCase
import com.example.binlist.bininfo.domain.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val getBinInfoUseCase: GetBinInfoUseCase
) : ViewModel() {

    private val _binInfo = MutableStateFlow<BinInfo?>(null)
    val binInfo: StateFlow<BinInfo?> = _binInfo

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun getBinInfo(bin: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getBinInfoUseCase(bin)
                _binInfo.value = result
                _error.value = null // Очистка ошибки при успешном запросе
            } catch (e: HttpException) {
                handleHttpException(e)
            } catch (e: IOException) {
                _error.value = "Проблема с подключением. Проверьте интернет."
            } catch (e: Exception) {
                _error.value = "Неизвестная ошибка. Попробуйте позже."
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun handleHttpException(e: HttpException) {
        when (e.code()) {
            400 -> _error.value = "Некорректный запрос. Проверьте данные."
            404 -> _error.value = "BIN не найден."
            429 -> _error.value = "Превышен лимит запросов. Попробуйте позже."
            500 -> _error.value = "Ошибка сервера. Повторите попытку позже."
            else -> _error.value = "Ошибка ${e.code()}: ${e.message()}"
        }
    }
}

