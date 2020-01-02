package com.example.apiintegrate.network

import com.example.apiintegrate.model.BaseRespone
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @Headers(
        "Accept:application/vnd.twitchtv.v5+json",
        "Client-ID:4mvsf8yizhms3aow1lnicx8fyrsbwt"
    )
    @GET("users")
     fun getUserInfoObs (@Query("login") userName: String) : Observable<BaseRespone>

    @Headers(
        "Accept:application/vnd.twitchtv.v5+json",
        "Client-ID:4mvsf8yizhms3aow1lnicx8fyrsbwt"
    )
    @GET("users")
    fun getUserInfoEnque (@Query("login") userName: String) : Call<BaseRespone>

    @Headers(
        "Accept:application/vnd.twitchtv.v5+json",
        "Client-ID:4mvsf8yizhms3aow1lnicx8fyrsbwt"
    )
    @GET("users")
    suspend fun getUserInfoCorou (@Query("login") userName: String) : Response<BaseRespone>

}