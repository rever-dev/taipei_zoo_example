package com.reverchen.taipei_zoo_example.NetWorkUtil

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

object APIService {
    
    const val BASE_URL =
        "https://data.taipei/api/v1/dataset/"
    
    interface Garden {
        
        @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
        fun getGardenList(): Call<ResponseBody?>
    }
    
    interface Plants {
    
    }
}