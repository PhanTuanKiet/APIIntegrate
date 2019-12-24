package com.example.apiintegrate.network

import com.example.apiintegrate.model.BaseRespone
import com.example.apiintegrate.model.UserInfo
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface APIService {

    @Headers(
        "Accept:application/vnd.twitchtv.v5+json",
        "Client-ID:4mvsf8yizhms3aow1lnicx8fyrsbwt"
    )
    @GET("users")
     fun getUserInfo (@Query("login") userName: String) : Call<BaseRespone>

}