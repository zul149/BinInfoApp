package com.example.binlist.bininfo.domain

import com.example.binlist.bininfo.data.BinHistory
import com.example.binlist.bininfo.data.BinRepository
import javax.inject.Inject

class DeleteHistoryUseCase @Inject constructor(
    private val binRepository: BinRepository
) {
    suspend operator fun invoke(binHistory: BinHistory) {
        // Удаление элемента из Room
        binRepository.deleteHistory(binHistory)
    }
}