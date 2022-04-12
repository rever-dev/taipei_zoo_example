package com.reverchen.taipei_zoo_example.InfoPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.reverchen.taipei_zoo_example.DataBean.Garden
import com.reverchen.taipei_zoo_example.Repository.RepositoryImpl

class InfoPageViewModel(application: Application) : AndroidViewModel(application) {
    
    val name = MutableLiveData<String?>()
    val imgUrl = MutableLiveData<String?>()
    val info = MutableLiveData<String?>()
    val memo = MutableLiveData<String?>()
    val category = MutableLiveData<String?>()
    val url = MutableLiveData<String?>()
    
    val gardensData = MutableLiveData<List<Garden>?>()
    
    val repo = RepositoryImpl(application.applicationContext)
    
    fun init(id: Int) {
        Thread {
            val garden = repo.localService.getGardenById(id) ?: return@Thread
            
            name.postValue(garden.E_Name)
            imgUrl.postValue(garden.E_Pic_URL)
            info.postValue(garden.E_Info)
            memo.postValue(garden.E_Memo)
            category.postValue(garden.E_Category)
            url.postValue(garden.E_URL)
            
            gardensData.postValue(
                repo.localService.getAllGarden()
            )
        }.start()
    }
}