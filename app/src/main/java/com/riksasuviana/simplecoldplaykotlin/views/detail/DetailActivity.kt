package com.riksasuviana.simplecoldplaykotlin.views.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.riksasuviana.simplecoldplaykotlin.R
import com.riksasuviana.simplecoldplaykotlin.model.TrackModel
import com.riksasuviana.simplecoldplaykotlin.utils.helper.CompanionHelper
import com.riksasuviana.simplecoldplaykotlin.views.detail.adapter.DetailAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        collapsing_toolbar.setCollapsedTitleTextAppearance(R.style.collapsedappbar)
        collapsing_toolbar.setExpandedTitleTextAppearance(R.style.expandedappbar)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get intent
        var albumid = getIntent().extras.getString("albumid")
        var albumname = getIntent().extras.getString("albumname")
        var albumthumb = getIntent().extras.getString("albumthumb")

        //set view from intent
        supportActionBar?.setTitle(albumname)
        if(!albumthumb.isNullOrEmpty()){
            Glide.with(this)
                    .load(albumthumb)
                    .into(iv_detail)
        }

        //baso
        baso_detail.startProgress()
        rv_detail.visibility = View.GONE

        //recyclerview

        var list:ArrayList<TrackModel.Track> = ArrayList()

        var adapter = DetailAdapter(list)

        rv_detail.adapter = adapter

        rv_detail.layoutManager = LinearLayoutManager(this)

        var api = CompanionHelper.getNetworkService()

        api.getTrack(albumid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(object:Observer<TrackModel>{
                    override fun onCompleted() {
                        baso_detail.stopAndGone()
                        rv_detail.visibility = View.VISIBLE
                    }

                    override fun onError(e: Throwable?) {
                        baso_detail.stopAndError(e?.message)
                    }

                    override fun onNext(t: TrackModel?) {
                        for(item in t?.track?.indices!!) {
                            var track = t.track!!.get(item)
                            list?.add(track)
                            adapter.notifyDataSetChanged()
                        }
                    }

                })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}
