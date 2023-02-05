package ru.headgrass.bininfo.model

class RepositoryImpl: Repository {
    override fun getInfoBinFromServer(): BinInfo = BinInfo()

    override fun getInfoBinFromLocalStorage(): BinInfo = getBinInfoNow()
}