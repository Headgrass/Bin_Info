package ru.headgrass.bininfo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BinInfo(
    val network: String = "NONE",
    val type: String = "NONE",
    val brand: String = "NONE",
    val prepaid: String = "NONE",
    val country: String = "NONE",
    val currency: String = "NONE",
    val bank: String = "NONE",
    val phone: String = "NONE",
    val city: String = "NONE",
    val site: String = "NONE"
) : Parcelable

fun getBinInfoNow(): BinInfo {
    return BinInfo(
        "Visa", "debit", "New World Immediate Debit", "false",
        "Russian Federation", "RUB", "Alfa-Bank", "+7 495 78-888-78",
        "Moscow", "www.alfabank.ru"
    )
}