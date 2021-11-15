package com.vmh.barberapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("center")
    @Expose
    var center: String? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("idCus")
    @Expose
    var idCus: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("role")
    @Expose
    var role: String? = null,
    @SerializedName("refreshToken")
    @Expose
    var refreshToken: String? = null,
    @SerializedName("token")
    @Expose
    var token: String? = null,
    @SerializedName("idCus_new")
    @Expose
    var idCusNew: String? = null,
    var username: String? = null,
    var password: String? = null,
) : Parcelable
