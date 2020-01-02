package com.example.apiintegrate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.example.apiintegrate.model.BaseRespone
import com.example.apiintegrate.network.APIService
import com.example.apiintegrate.network.APIUtils
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.*


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

        btnRunRx.setOnClickListener {
            getUserInfo(1)
        }

        btnRunRetro.setOnClickListener {
            getUserInfo(2)
        }

        btnRunCorou.setOnClickListener {
            getUserInfo(3)
        }

        btnClear.setOnClickListener {
            tvName.text = ""
            tvBio.text = ""
        }
    }


    fun getUserInfo(key : Int) {

        if (key == 1) {
            var userObservable : Observable<BaseRespone> = mAPIService.getUserInfoObs(USERNAME)

            userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    tvName.text = it.user[0].name
                    tvBio.text = it.user[0].bio
                },Throwable::printStackTrace)
        } else if (key == 2) {
            mAPIService.getUserInfoEnque(USERNAME).enqueue(
                object : Callback<BaseRespone>{
                    override fun onFailure(call: Call<BaseRespone>, t: Throwable) {
                        Log.i("get API failed",t.message.toString())
                    }

                    override fun onResponse(call: Call<BaseRespone>, response: Response<BaseRespone>) {
                            tvName.text = response.body()!!.user[0].name
                            tvBio.text = response.body()!!.user[0].bio
                    }
                }
            )
        } else {
            GlobalScope.launch {
                val respone  = getInfoCorou(USERNAME)
                runOnUiThread {
                    tvName.text = respone.body()!!.user[0].name
                    tvBio.text = respone.body()!!.user[0].bio
                }

            }

        }
    }

    private suspend fun getInfoCorou(userName : String) : Response<BaseRespone> {
        return mAPIService.getUserInfoCorou(userName)
    }
}
