package ru.headgrass.bininfo.model

interface Repository {
    fun getInfoBinFromServer(): BinInfo
    fun getInfoBinFromLocalStorage(): BinInfo
}