package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Imaduddin Al Fikri on 18/04/18.
 */

open class BaseResponse<T> {

    @SerializedName("end")
    @Expose
    var status: String = ""

    @SerializedName("data")
    @Expose
    var data: T? = null

    @SerializedName("message")
    @Expose
    var message: String = ""
}

open class EmptyObject {

}