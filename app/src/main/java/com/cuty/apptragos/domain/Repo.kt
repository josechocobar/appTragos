package com.cuty.apptragos.domain

import com.cuty.apptragos.data.data.Drink
import com.cuty.apptragos.data.data.DrinkEntity
import com.cuty.apptragos.vo.Resource

interface Repo {
    suspend fun getTragosList(tragoName:String ) : Resource<List<Drink>>
    suspend fun getTragosFavoritos():Resource<List<DrinkEntity>>
    suspend fun insertTrago(trago:DrinkEntity)
}