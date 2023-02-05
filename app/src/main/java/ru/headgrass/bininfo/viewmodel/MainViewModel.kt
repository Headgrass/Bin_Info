package ru.headgrass.bininfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.headgrass.bininfo.model.BinInfo
import ru.headgrass.bininfo.model.Repository
import ru.headgrass.bininfo.model.RepositoryImpl
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getBinInfo() {

        Thread {
            Thread.sleep(1000)
            if (Random.nextBoolean()) {
                val binInfo = repo.getInfoBinFromServer()
                liveDataToObserve.postValue(AppState.Success(binInfo))
            } else {
                liveDataToObserve.postValue(AppState.Error(Exception("")))
            }
        }.start()
    }
}