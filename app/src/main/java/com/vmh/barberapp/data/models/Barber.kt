package com.vmh.barberapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "barbershop")
data class Barber(
    @PrimaryKey
    var id: String
) : Parcelable
