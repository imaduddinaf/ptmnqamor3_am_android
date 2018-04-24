package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.utils

import kotlin.reflect.KClass

/**
 * Created by Imaduddin Al Fikri on 24/04/18.
 */

val<T: Any> T.kClass: KClass<T>
    get() = javaClass.kotlin

class Constant {

    companion object {
        val TITLE = "Pertamina Questionnaire MOR III"
        val DEBUG_TAG = "PHA - DEBUG"
        val ERROR_TAG = "PHA - ERROR"
        val DEFAULT_LOADING_MESSAGE = "Harap tunggu..."
    }
}