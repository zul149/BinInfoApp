package com.example.binlist.bininfo.data

// Интерфейс репозитория
interface BinRepository {

    // Получение информации по BIN с API и сохранение в Room
    suspend fun getBinInfo(bin: String): BinInfo

    // Получение истории запросов из Room
    suspend fun getHistory(): List<BinHistory>

    // Удаление истории по элементу BIN из Room
    suspend fun deleteHistory(binHistory: BinHistory)
}