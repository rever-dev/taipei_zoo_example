package com.reverchen.taipei_zoo_example.InfoPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.reverchen.taipei_zoo_example.DataBean.Garden
import com.reverchen.taipei_zoo_example.Repository.RepositoryImpl

class InfoPageViewModel(application: Application) : AndroidViewModel(application) {
    
    val isLoading = MutableLiveData<Boolean>()
    
    val name = MutableLiveData<String?>()
    val imgUrl = MutableLiveData<String?>()
    val info = MutableLiveData<String?>()
    val memo = MutableLiveData<String?>()
    val category = MutableLiveData<String?>()
    val url = MutableLiveData<String?>()
    
    val gardensData = MutableLiveData<List<Garden>?>()
    
    val repo = RepositoryImpl(application.applicationContext)
    
    fun init(id: Int) {
        isLoading.value = true
        Thread {
            val garden = repo.localService.getGardenById(id) ?: return@Thread
    
            name.postValue(garden.E_Name)
            imgUrl.postValue(garden.E_Pic_URL)
            info.postValue(garden.E_Info)
            memo.postValue(garden.E_Memo)
            category.postValue(garden.E_Category)
            url.postValue(garden.E_URL)
    
            val gardens = repo.localService.getAllGarden()
            if (gardens.isNullOrEmpty()) return@Thread
    
            /* random fetching garden data to display recycler view */
            val randomList = mutableListOf<Garden>()
            while (randomList.size != 3) {
                val randomInt = (Math.random() * gardens.size + 1).toInt()
                val randomGarden = repo.localService.getGardenById(randomInt) ?: break
                if (randomGarden._id != id && randomList.contains(randomGarden).not()) {
                    randomList.add(randomGarden)
                }
            }
    
            gardensData.postValue(randomList)
    
            isLoading.postValue(false)
        }.start()
    }
}