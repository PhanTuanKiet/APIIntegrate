package com.example.apiintegrate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserInfo(@SerializedName("display_name") @Expose var name : String,
                    @SerializedName("bio") @Expose var bio : String,
                    @SerializedName("logo") @Expose var avatar : String)