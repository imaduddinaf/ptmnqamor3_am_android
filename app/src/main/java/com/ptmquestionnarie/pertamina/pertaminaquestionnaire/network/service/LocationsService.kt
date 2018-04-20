package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.service

import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.BaseResponse
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.PLocation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Imaduddin Al Fikri on 18/04/18.
 */

interface LocationService {

    @GET("locations")
    fun getLocations(@Query("parent") parentID: Long): Observable<BaseResponse<Array<PLocation>>>
}