package com.reverchen.taipei_zoo_example.NetWorkUtil.APIResponse

data class BaseResponse<T : SubInfo>(
    var result: Result<T>?
) {
    class Result<T>(
        var count: Int?,
        var limit: Int?,
        var offset: Int?,
        var results: List<T>?,
        var sort: String?
    ) {
        class GardenType(
            var E_Category: String?,
            var E_Geo: String?,
            var E_Info: String?,
            var E_Memo: String?,
            var E_Name: String?,
            var E_Pic_URL: String?,
            var E_URL: String?,
            var E_no: String?,
            var _id: Int?
        ) : SubInfo()
        
        class FlantsType(
            var F_AlsoKnown: String?,
            var F_Brief: String?,
            var F_CID: String?,
            var F_Code: String?,
            var F_Family: String?,
            var F_Feature: String?,
            var F_Function_Application: String?,
            var F_Genus: String?,
            var F_Geo: String?,
            var F_Keywords: String?,
            var F_Location: String?,
            var F_Name_En: String?,
            var F_Name_Latin: String?,
            var F_Pic01_ALT: String?,
            var F_Pic01_URL: String?,
            var F_Pic02_ALT: String?,
            var F_Pic02_URL: String?,
            var F_Pic03_ALT: String?,
            var F_Pic03_URL: String?,
            var F_Pic04_ALT: String?,
            var F_Pic04_URL: String?,
            var F_Summary: String?,
            var F_Update: String?,
            var F_Vedio_URL: String?,
            var F_Voice01_ALT: String?,
            var F_Voice01_URL: String?,
            var F_Voice02_ALT: String?,
            var F_Voice02_URL: String?,
            var F_Voice03_ALT: String?,
            var F_Voice03_URL: String?,
            var F_pdf01_ALT: String?,
            var F_pdf01_URL: String?,
            var F_pdf02_ALT: String?,
            var F_pdf02_URL: String?,
            var _id: Int?,
            var F_Name_Ch: String?
        ) : SubInfo()
    }
}

abstract class SubInfo


