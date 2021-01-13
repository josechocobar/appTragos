package com.cuty.apptragos.data.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val tragoId: String = "",
    @SerializedName("strDrinkThumb")
    var imagen: String = "",
    @SerializedName("strDrink")
    var nombre: String = "",
    @SerializedName("strInstructions")
    var descripcion: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol:String = "Non_Alcoholic"

) : Parcelable

data class DrinkList(@SerializedName("drinks")
                     val drinkList: List<Drink>)

@Entity
data class DrinkEntity(
    @PrimaryKey
    val tragoId : String,
    @ColumnInfo(name = "trago_imagen")
    var imagen: String = "",
    @ColumnInfo(name = "trago_nombre")
    var nombre: String = "",
    @ColumnInfo(name = "trago_descripcion")
    var descripcion: String = "",
    @ColumnInfo(name = "trago_has_alcohol")
    val hasAlcohol:String = "Non_Alcoholic"
)