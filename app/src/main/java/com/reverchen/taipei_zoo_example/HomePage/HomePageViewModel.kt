package com.reverchen.taipei_zoo_example.HomePage

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.reverchen.taipei_zoo_example.DataBean.Garden
import com.reverchen.taipei_zoo_example.Repository.RepositoryImpl

class HomePageViewModel(application: Application) : AndroidViewModel(application) {
    
    val isLoading = MutableLiveData<Boolean>()
    var baseGardens = MutableLiveData<List<Garden>?>()
    val filterGardens = MutableLiveData<List<Garden>?>()
    
    private val repo: RepositoryImpl = RepositoryImpl(application.applicationContext)
    
    init {
        isLoading.value = true
        Thread {
            val response = repo.remoteService.getGardenList()
            if (response != null) {
                
                for (resGarden in response.result!!.results!!) {
                    val garden = Garden.convert(resGarden)
                    repo.localService.insertGarden(garden)
                }
                
                val gardens = repo.localService.getAllGarden()
                
                Log.d(this.toString(), "garden list: $gardens")
                
                baseGardens.postValue(gardens)
                isLoading.postValue(false)
            } else {
                baseGardens.postValue(null)
                isLoading.postValue(false)
            }
        }.start()
    }
    
    fun filteringData(filtString: String) {
        val filtList = baseGardens.value?.filter { garden ->
            garden.E_Category == filtString
        }
        filterGardens.postValue(filtList)
    }
}