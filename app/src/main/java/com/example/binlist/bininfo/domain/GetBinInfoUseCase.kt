package com.example.binlist.bininfo.domain

import com.example.binlist.bininfo.data.BinInfo
import com.example.binlist.bininfo.data.BinRepository
import javax.inject.Inject

class GetBinInfoUseCase @Inject constructor(
    private val binRepository: BinRepository
) {
    suspend operator fun invoke(bin: String): BinInfo {
        // Получаем информацию о BIN из API и сохраняем в Room
        return binRepository.getBinInfo(bin)
    }
}