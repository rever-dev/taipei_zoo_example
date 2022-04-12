package com.sttptech.toshiba_lighting.Data.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reverchen.taipei_zoo_example.DataBean.Garden
import com.sttptech.toshiba_lighting.Data.Room.DataBase.Companion.DB_VERSION

@Database(
    entities = [Garden::class],
    version = DB_VERSION,
    exportSchema = false
) //資料綁定的Getter-Setter,資料庫版本,是否將資料導出至文件
abstract class DataBase : RoomDatabase() {
    //設置對外接口
    abstract val dataUao: DataDao

    companion object {
        private const val DB_NAME = "data_base" // 資料庫名稱
        const val DB_VERSION = 4 // 資料庫版本

        const val GARDEN_TABLE_NAME = "GARDEN_TABLE"

        @Volatile
        private var instance: DataBase? = null

        @Synchronized
        fun getInstance(context: Context): DataBase {
            if (instance == null) {
                instance = create(context) //創立新的資料庫
            }
            return instance!!
        }

        private fun create(context: Context): DataBase {
            return Room.databaseBuilder(
                context,
                DataBase::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}