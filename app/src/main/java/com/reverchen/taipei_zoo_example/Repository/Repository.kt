package com.reverchen.taipei_zoo_example.Repository

import com.reverchen.taipei_zoo_example.DataBean.Garden
import com.reverchen.taipei_zoo_example.NetWorkUtil.APIResponse.BaseResponse

interface Repository {
    
    interface LocalService {
        
        fun getAllGarden(): List<Garden>?
        
        fun getGardenByCategory(category: String): List<Garden>?
        
        fun getGardenById(id: Int): Garden?
        
        fun insertGarden(data: Garden)
    }
    
    interface RemoteService {
        
        fun getGardenList(): BaseResponse<BaseResponse.Result.GardenType>?
    }
}