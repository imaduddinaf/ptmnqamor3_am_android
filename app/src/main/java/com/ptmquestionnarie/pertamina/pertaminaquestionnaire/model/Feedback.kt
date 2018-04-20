package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Imaduddin Al Fikri on 18/04/18.
 */

open class Feedback: BaseModel() {

    @SerializedName("user")
    @Expose
    var user: User? = null

    @SerializedName("location")
    @Expose
    var location: PLocation = PLocation()
}