package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Imaduddin Al Fikri on 18/04/18.
 */

open class Feedback: BaseModel() {

    @SerializedName("is_like")
    @Expose
    var isLike: Boolean = false

    @SerializedName("location_id")
    @Expose
    var locationID: Long = 0
}