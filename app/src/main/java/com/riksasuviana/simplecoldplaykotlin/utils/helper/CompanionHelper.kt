package com.riksasuviana.simplecoldplaykotlin.utils.helper

import com.riksasuviana.simplecoldplaykotlin.utils.network.ApiClient
import com.riksasuviana.simplecoldplaykotlin.utils.network.NetworkService

/**
 * Created by riksa on 25/11/2017.
 */
class CompanionHelper {
    companion object {
        val BASE_URL: String = "http://www.theaudiodb.com/"

        fun getNetworkService():NetworkService{
            return ApiClient.request().create(NetworkService::class.java)
        }
    }
}