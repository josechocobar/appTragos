package com.cuty.apptragos.domain

import com.cuty.apptragos.data.data.DataSource
import com.cuty.apptragos.data.data.Drink
import com.cuty.apptragos.data.data.DrinkEntity
import com.cuty.apptragos.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {
     override suspend fun getTragosList(tragoName:String): Resource<List<Drink>> {
        return dataSource.getTragoByName(tragoName)
    }

    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return dataSource.getTragosFavoritos()
    }

    override suspend fun insertTrago(trago: DrinkEntity) {
        dataSource.insertTragoIntoRoom(trago)
    }
}