package com.reverchen.taipei_zoo_example.Repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.reverchen.taipei_zoo_example.DataBean.Garden
import com.reverchen.taipei_zoo_example.NetWorkUtil.APIResponse.BaseResponse
import com.reverchen.taipei_zoo_example.NetWorkUtil.APIService
import com.sttptech.toshiba_lighting.Data.Room.DataBase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RepositoryImpl(context: Context) {
    
    companion object {
        const val LOCAL_SERVICE = "Local_service"
        const val REMOTE_SERVICE = "Remote_service"
    }
    
    val localService = LocalServiceImpl(context)
    val remoteService = RemoteServiceImpl()
    
    inner class LocalServiceImpl(context: Context) : Repository.LocalService {
        
        private val dao = DataBase.getInstance(context).dataUao
        
        override fun getAllGarden(): List<Garden>? {
            return dao.getAllGarden()
        }
    
        override fun getGardenByCategory(category: String): List<Garden>? {
            return dao.getGardenByCategory(category)
        }
    
        override fun getGardenById(id: Int): Garden? {
            return dao.getGardenById(id)
        }
    
        override fun insertGarden(data: Garden) {
            dao.insert_garden(data)
        }
    }
    
    inner class RemoteServiceImpl : Repository.RemoteService {
        
        private val retrofit = Retrofit.Builder()
            .baseUrl(APIService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        
        override fun getGardenList(): BaseResponse<BaseResponse.Result.GardenType>? {
            
            val call = retrofit
                .create(APIService.Garden::class.java)
                .getGardenList()
    
            try {
                val response = call.execute()
        
                if (response.isSuccessful.not() || response.body() == null) return null
        
                val resStr = response.body()!!.string()
                val type =
                    object : TypeToken<BaseResponse<BaseResponse.Result.GardenType>>() {}.type
                
                return Gson().fromJson(resStr, type)
                
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            } catch (e: NullPointerException) {
                e.printStackTrace()
                return null
            }
        }
    }
}