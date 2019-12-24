package com.example.apiintegrate

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apiintegrate.model.BaseRespone
import com.example.apiintegrate.model.UserInfo
import com.example.apiintegrate.network.APIService
import com.example.apiintegrate.network.APIUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    companion object{
        const val USERNAME = "megido_thanatos"
    }

    lateinit var compositeDisposable : CompositeDisposable
    lateinit var mAPIService : APIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()
        mAPIService = APIUtils.getAPIService()

        btnRun.setOnClickListener { getUserInfo() }
    }


    fun getUserInfo() {
        mAPIService.getUserInfo(USERNAME).enqueue(
            object : Callback<BaseRespone>{
                override fun onFailure(call: Call<BaseRespone>, t: Throwable) {
                    Log.i("get API failed",t.message.toString());
                }

                override fun onResponse(call: Call<BaseRespone>, response: Response<BaseRespone>) {
                    runOnUiThread {
                        tvName.text = response.body()!!.user[0].name
                        tvBio.text = response.body()!!.user[0].bio
                    }

                }
            }
        )

    }
}
