package com.example.binlist.bininfo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Сущность для таблицы истории запросов по BIN
@Entity
data class BinHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val bin: String,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val countryName: String?,
    val countryCode: String?,
    val bankName: String?,
    val url: String?,
    val city: String?,
)

fun BinInfo.toBinHistory(bin: String): BinHistory {
    return BinHistory(
        bin = bin,
        scheme = this.scheme,
        type = this.type,
        brand = this.brand,
        countryName = this.country?.name,
        countryCode = this.country?.alpha2,
        bankName = this.bank?.name,
        url = this.bank?.url,
        city = this.bank?.city,
    )
}