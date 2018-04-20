package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.utils

import android.util.Log

/**
 * Created by Imaduddin Al Fikri on 18/04/18.
 */

class PLogger {

    companion object {

        fun show(message: String) {
            Log.d("DEBUG", message)
        }

        fun showSuccess(message: String) {
            Log.d("SUCCESS", message)
        }

        fun showError(message: String) {
            Log.e("ERROR", message)
        }
    }
}