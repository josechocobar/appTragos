package com.cuty.apptragos.data.data

import com.cuty.apptragos.AppDatabase
import com.cuty.apptragos.vo.Resource
import com.cuty.apptragos.vo.RetrofitClient

class DataSource (private val appDatabase: AppDatabase){
    suspend fun getTragoByName(tragoName:String) : Resource<List<Drink>> {
        return Resource.Success( RetrofitClient.webservice.getTragoByName(tragoName).drinkList)
    }
    suspend fun insertTragoIntoRoom(trago:DrinkEntity){
        appDatabase.tragoDao().insertFavorite(trago)
    }

    suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDatabase.tragoDao().getAllFavoriteDrinks())
    }

}