package ru.headgrass.bininfo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BinInfoDTO(
    @SerializedName("scheme")
    val network: String = "NONE",
    @SerializedName("type")
    val type: String = "NONE",
    @SerializedName("brand")
    val brand: String = "NONE",
    @SerializedName("prepaid")
    val prepaid: String = "NONE",
    @SerializedName("country")
    val countryDTO: CountryDTO?,
    @SerializedName("bank")
    val bankDTO: BankDTO?
) : Parcelable

@Parcelize
data class BankDTO(
    @SerializedName("name")
    val bank: String = "NONE",
    val phone: String = "NONE",
    val city: String = "NONE",
    @SerializedName("url")
    val site: String = "NONE"
) : Parcelable

@Parcelize
data class CountryDTO(
    val numeric: String = "NONE",
    @SerializedName("name")
    val country: String = "NONE",
    val currency: String = "NONE"
) : Parcelable