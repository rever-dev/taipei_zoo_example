package com.sttptech.toshiba_lighting.Data.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reverchen.taipei_zoo_example.DataBean.Garden

@Dao
interface DataDao {
    
    /* insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert_garden(data: Garden)
    
    
    /** get data */
    @Query("SELECT * FROM " + DataBase.GARDEN_TABLE_NAME)
    fun getAllGarden(): List<Garden>?
    
    @Query("SELECT * FROM " + DataBase.GARDEN_TABLE_NAME + " WHERE `E_Category` = :category")
    fun getGardenByCategory(category: String): List<Garden>?
    
    @Query("SELECT * FROM " + DataBase.GARDEN_TABLE_NAME + " WHERE `_id` = :id")
    fun getGardenById(id: Int): Garden?
    
//    /*=======================================================================================*/
//
//    /** 簡易更新資料的方法 */
//    @Update
//    fun updateCeilingLight(data: CeilingLight)
//
//    @Update
//    fun updateGroup(data: Group)
//
//    @Update
//    fun updateScene(data: Scene)
    
    /*======================================================================================= */
    
//    /** 簡單刪除資料的方法 */
//    @Query("DELETE FROM " + DataBase.CEILING_LIGHT_TABLE_NAME + " WHERE `uId` = :uId")
//    fun deleteCeilingLightByUId(uId: String)
//
//    @Delete
//    fun deleteGroups(data: Group)
//
//    @Query("DELETE FROM " + DataBase.SCENE_TABLE_NAME + " WHERE `uId` = :uId")
//    fun deleteSceneByUId(uId: String)
//
//    @Query("DELETE FROM " + DataBase.SCENE_TABLE_NAME + " WHERE `seq` = :seq")
//    fun deleteSceneBySeq(seq: Int): Int
//
//    @Query("DELETE FROM " + DataBase.CEILING_LIGHT_TABLE_NAME)
//    fun deleteCeilingLightTable()
//
//    @Query("DELETE FROM " + DataBase.GROUPS_TABLE_NAME)
//    fun deleteGroupsTable()
//
//    @Query("DELETE FROM " + DataBase.SCENE_TABLE_NAME)
//    fun deleteSceneTable()
}