package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho

/**
 * Created by Imaduddin Al Fikri on 18/04/18.
 */

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        Fresco.initialize(this)
    }
}