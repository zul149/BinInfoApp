package com.example.binlist.bininfo.data

// Реализация репозитория
class BinRepositoryImpl(
    private val binApi: BinApi,
    private val binDao: BinDao
) : BinRepository {

    // Получение информации по BIN с API и сохранение в Room
    override suspend fun getBinInfo(bin: String): BinInfo {
        // Получаем данные с API
        val binInfo = binApi.getBinInfo(bin)

        // Сохраняем данные в базу данных
        binDao.insert(binInfo.toBinHistory(bin))

        return binInfo
    }

    // Получение истории запросов из Room
    override suspend fun getHistory(): List<BinHistory> {
        return binDao.getAll()
    }

    // Удаление истории по элементу BIN из Room
    override suspend fun deleteHistory(binHistory: BinHistory) {
        binDao.delete(binHistory) // Вызываем метод DAO для удаления
    }
}