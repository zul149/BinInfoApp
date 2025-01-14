package com.example.binlist.bininfo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

// Класс базы данных Room
@Database(entities = [BinHistory::class], version = 1, exportSchema = false)
abstract class BinDatabase : RoomDatabase() {

    // Абстрактный метод для доступа к DAO
    abstract fun binDao(): BinDao

}