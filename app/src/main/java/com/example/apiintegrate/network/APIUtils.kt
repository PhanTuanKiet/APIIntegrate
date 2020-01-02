package com.example.apiintegrate.network

class APIUtils {
    companion object {

        const val BASE_URL = "https://api.twitch.tv/kraken/"

        fun getAPIService() : APIService {
            return RetrofitClient.getClient(BASE_URL)!!.create(APIService::class.java)
        }


    }
}