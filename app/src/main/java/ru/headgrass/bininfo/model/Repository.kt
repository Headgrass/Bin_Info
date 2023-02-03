package ru.headgrass.bininfo.model

import ru.headgrass.bininfo.BinInfo

interface Repository {
    fun getInfoBinFromServer(): BinInfo
    fun getInfoBinFromLocalStorage(): List<BinInfo>
}