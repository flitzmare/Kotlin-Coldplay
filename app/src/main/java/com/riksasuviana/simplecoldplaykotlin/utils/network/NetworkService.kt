package com.riksasuviana.simplecoldplaykotlin.utils.network

import com.iak.riksasuviana.coldplaytestgits.model.AlbumModel
import com.riksasuviana.simplecoldplaykotlin.model.TrackModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by riksa on 25/11/2017.
 */
interface NetworkService {
    @GET("api/v1/json/1/album.php?i=111239")
    fun getAlbum(): Observable<AlbumModel>

//    2109614
    @GET("api/v1/json/1/track.php?")
    fun getTrack(@Query("m") albumid:String): Observable<TrackModel>
}