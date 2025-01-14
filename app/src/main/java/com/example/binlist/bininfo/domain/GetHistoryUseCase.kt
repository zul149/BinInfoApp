package com.example.binlist.bininfo.domain

import com.example.binlist.bininfo.data.BinHistory
import com.example.binlist.bininfo.data.BinRepository
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val binRepository: BinRepository
) {
    suspend operator fun invoke(): List<BinHistory> {
        // Получаем всю историю запросов из Room
        return binRepository.getHistory()
    }
}