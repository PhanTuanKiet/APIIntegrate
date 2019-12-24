package com.example.apiintegrate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseRespone(@SerializedName("total") @Expose var total : Int,
                       @SerializedName("users") @Expose var user : Array<UserInfo>)