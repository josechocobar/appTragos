package com.cuty.apptragos.domain

import com.cuty.apptragos.data.data.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getTragoByName(@Query("s") tragoName: String): DrinkList
}