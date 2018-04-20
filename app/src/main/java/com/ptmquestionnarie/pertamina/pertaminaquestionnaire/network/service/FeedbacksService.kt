package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.service

import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.BaseResponse
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.EmptyObject
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Imaduddin Al Fikri on 18/04/18.
 */

interface FeedbackService {

    @POST("feedback")
    @FormUrlEncoded
    fun postFeedback(@Field("userId") userID: Long,
                     @Field("feedback") feedback: Boolean): Observable<BaseResponse<EmptyObject>>
}