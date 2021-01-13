package com.cuty.apptragos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cuty.apptragos.data.data.DrinkEntity
import com.cuty.apptragos.domain.TragosDao

@Database(entities = arrayOf(DrinkEntity::class),version = 1)
abstract class AppDatabase :RoomDatabase() {
    abstract fun tragoDao():TragosDao

    companion object{
        private var INSTANCE : AppDatabase? = null
        fun getDatabase(context: Context):AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"tabla_tragos").build()
            return INSTANCE!!
        }
        fun destroyInstance(){
            INSTANCE = null
        }

    }
}