package com.riksasuviana.simplecoldplaykotlin.views.main

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.iak.riksasuviana.coldplaytestgits.model.AlbumModel
import com.riksasuviana.simplecoldplaykotlin.R
import com.riksasuviana.simplecoldplaykotlin.views.main.adapter.MainAdapter
import com.riksasuviana.simplecoldplaykotlin.utils.helper.CompanionHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.runBlocking
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var progressDialog = ProgressDialog(this);
        progressDialog.setMessage("Please wait...")
        progressDialog.setCancelable(false)

        //recyclerview

        var list = ArrayList<AlbumModel.Album>()
        var adapter = MainAdapter(list, this)
        var gridLayoutManager = GridLayoutManager(this, 2)

        rv_main.adapter = adapter
        rv_main.layoutManager = gridLayoutManager

        //api
        var api = CompanionHelper.getNetworkService()

        progressDialog.show()

        api.getAlbum().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(object:Observer<AlbumModel>{
                    override fun onCompleted() {
                        progressDialog.dismiss()
                    }

                    override fun onError(e: Throwable?) {
                        progressDialog.dismiss()
                        Toast.makeText(baseContext, e?.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onNext(t: AlbumModel?) {
                        for(item in t?.album!!.indices) {
                                    var album = t.album!!.get(item)
                                    list.add(album)
                                    adapter.notifyDataSetChanged()
                        }
                    }
                })
//                .enqueue(object:Callback<AlbumModel>{
//            override fun onResponse(call: Call<AlbumModel>?, response: Response<AlbumModel>?) {
//                if(response != null) {
//                    if (response.isSuccessful) {
//                        for(item in response.body()?.album!!.indices) {
//                            var album = response.body()?.album!!.get(item)
//                            list.add(album)
//                            adapter.notifyDataSetChanged()
//                        }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<AlbumModel>?, t: Throwable?) {
//
//            }
//        })

        //test
        val actual = runBlocking {

        }
    }

    fun <T>async(block:suspend () -> T){

    }
}
