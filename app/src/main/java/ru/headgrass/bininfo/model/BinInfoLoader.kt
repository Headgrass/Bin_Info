package ru.headgrass.bininfo.model

import ru.headgrass.bininfo.BinInfo

object BinInfoLoader {

    fun load(info: BinInfo) {

    }

    interface OnBinInfoLoadListener {
        fun onLoaded(binInfo: BinInfoDTO)
        fun onFailed(throwable: Throwable)
    }
}