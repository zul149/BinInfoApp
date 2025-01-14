package com.example.binlist.bininfo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// DAO для работы с Room
@Dao
interface BinDao {

    // Вставляем или обновляем запись в истории запросов
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // Метод для добавления записи о BIN в базу данных.
    suspend fun insert(binHistory: BinHistory)

    // Получаем все записи из истории запросов
    @Query("SELECT * FROM BinHistory")
    // Метод для получения всех записей из таблицы
    suspend fun getAll(): List<BinHistory>

    // Удаляем запись из истории запросов
    @Delete
    suspend fun delete(binHistory: BinHistory)
}