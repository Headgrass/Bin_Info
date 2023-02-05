package ru.headgrass.bininfo.model

import com.google.gson.annotations.SerializedName

data class BinInfoDTO(
    @SerializedName("scheme")
    val network: String = "NONE",
    @SerializedName("type")
    val type: String = "NONE",
    @SerializedName("brand")
    val brand: String = "NONE",
    @SerializedName("prepaid")
    val prepaid: String = "NONE",
    val country: CountryDTO?,
    val bankDTO: BankDTO?
)

data class BankDTO(
    @SerializedName("name")
    val bank: String = "NONE",
    val phone: String = "NONE",
    val city: String = "NONE",
    @SerializedName("url")
    val site: String = "NONE"
)

data class CountryDTO(
    val numeric: String = "NONE",
    @SerializedName("name")
    val country: String = "NONE",
    val currency: String = "NONE"
)