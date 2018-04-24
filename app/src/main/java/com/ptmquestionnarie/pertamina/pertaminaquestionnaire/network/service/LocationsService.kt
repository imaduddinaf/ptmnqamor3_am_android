package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.service

import android.location.Location
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.BaseResponse
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.Feedback
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.PLocation
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Imaduddin Al Fikri on 18/04/18.
 */

interface LocationsService {

    @GET("locations")
    fun getLocations(): Observable<BaseResponse<Array<PLocation>>>

    @GET("locations")
    fun getLocations(@Field("parent_id") parentID: Long): Observable<BaseResponse<Array<PLocation>>>

    @GET("locations/{id}")
    fun getLocation(@Path("id") id: Long): Observable<BaseResponse<Array<PLocation>>>
}