package com.cuty.apptragos.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuty.apptragos.data.data.DrinkEntity
@Dao
interface TragosDao {
    @Query("SELECT * FROM DrinkEntity")
    suspend fun getAllFavoriteDrinks() : List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(trago:DrinkEntity)

}