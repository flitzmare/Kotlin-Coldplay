package com.riksasuviana.simplecoldplaykotlin.views.detail.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.riksasuviana.simplecoldplaykotlin.R
import com.riksasuviana.simplecoldplaykotlin.model.TrackModel
import kotlinx.android.synthetic.main.row_detail.view.*

/**
 * Created by riksa on 06/12/2017.
 */
class DetailAdapter(val list:List<TrackModel.Track>) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItems(list!!.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.row_detail, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model : TrackModel.Track){
            if(model.strTrackThumb.isNullOrEmpty()){
                itemView.iv_row_detail.setImageResource(R.drawable.coldplaylogo)
            }else {
                Glide.with(itemView)
                        .load(model.strTrackThumb)
                        .into(itemView.iv_row_detail)
            }
            if(model.strDescriptionEN.isNullOrEmpty()){
                itemView.tv_desc_row_detail.text = itemView.context.getString(R.string.description_dummy)
            }else{
                itemView.tv_desc_row_detail.text = model.strDescriptionEN
            }
            itemView.tv_title_row_detail.text = model.strTrack
        }
    }

}