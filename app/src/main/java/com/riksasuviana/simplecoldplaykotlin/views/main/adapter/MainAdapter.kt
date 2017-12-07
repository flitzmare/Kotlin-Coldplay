package com.riksasuviana.simplecoldplaykotlin.views.main.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iak.riksasuviana.coldplaytestgits.model.AlbumModel
import com.riksasuviana.simplecoldplaykotlin.R
import com.riksasuviana.simplecoldplaykotlin.views.detail.DetailActivity
import kotlinx.android.synthetic.main.row_main.view.*

/**
 * Created by riksa on 25/11/2017.
 */
class MainAdapter(list:List<AlbumModel.Album>, activity:Activity) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    var list:List<AlbumModel.Album>
    var activity:Activity?=null

    init {
        this.list = list
        this.activity = activity
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list.get(position), activity)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_main, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model: AlbumModel.Album, ac:Activity?){
            if(!model.strAlbumThumb.isNullOrEmpty()) {

                Glide.with(itemView.context)
                        .load(model.strAlbumThumb)
                        .apply(RequestOptions().fitCenter())
                        .into(itemView.iv_row_main)
            }else{
                itemView.iv_row_main.setImageResource(R.drawable.coldplaylogo)
            }

            itemView.tv_row_main_title.text = model.strAlbum

            itemView.setOnClickListener(View.OnClickListener {
                var options: ActivityOptions?= null
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation(ac, itemView.iv_row_main, "picture")
                }
                var intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("albumid", model.idAlbum)
                intent.putExtra("albumname", model.strAlbum)
                intent.putExtra("albumthumb", model.strAlbumThumb)
                itemView.context.startActivity(intent, options?.toBundle())
            })
        }
    }

}