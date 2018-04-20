package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.service

import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.BaseResponse
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.EmptyObject
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.Feedback
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Imaduddin Al Fikri on 18/04/18.
 */

interface FeedbacksService {

    @POST("feedbacks")
    @FormUrlEncoded
    fun postFeedback(@Field("is_like") isLike: Boolean,
                     @Field("location_id") locationID: Long): Observable<BaseResponse<Feedback>>

    @GET("feedbacks")
    fun getFeedbacks(@Field("location_id") locationID: Long): Observable<BaseResponse<Array<Feedback>>>


    @GET("feedbacks/{id}")
    fun getFeedback(@Path("id") id: Long): Observable<BaseResponse<Feedback>>
}