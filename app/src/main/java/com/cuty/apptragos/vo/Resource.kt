package com.cuty.apptragos.vo

import com.cuty.apptragos.data.data.Drink
import com.cuty.apptragos.data.data.DrinkList


sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data : T) : Resource<T>()
    data class Failure<out T>(val exception : T) : Resource<T>()
    //famosa sealed class que permitira
}