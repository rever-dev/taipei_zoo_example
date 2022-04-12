package com.reverchen.taipei_zoo_example.DataBean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reverchen.taipei_zoo_example.NetWorkUtil.APIResponse.BaseResponse
import com.sttptech.toshiba_lighting.Data.Room.DataBase

@Entity(tableName = DataBase.GARDEN_TABLE_NAME)
data class Garden(
    var E_Category: String?,
    var E_Geo: String?,
    var E_Info: String?,
    var E_Memo: String?,
    var E_Name: String?,
    var E_Pic_URL: String?,
    var E_URL: String?,
    var E_no: String?,
    @PrimaryKey
    var _id: Int?
) {
    companion object {
        fun convert(response: BaseResponse.Result.GardenType): Garden {
            return Garden(
                response.E_Category,
                response.E_Geo,
                response.E_Info,
                response.E_Memo,
                response.E_Name,
                response.E_Pic_URL?.replaceFirst("http", "https"),
                response.E_URL?.replaceFirst("http", "https"),
                response.E_no,
                response._id
            )
        }
    }
}