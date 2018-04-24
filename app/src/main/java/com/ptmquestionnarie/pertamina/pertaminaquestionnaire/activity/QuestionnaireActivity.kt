package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.activity

import android.os.Bundle
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.R
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core.BaseActivity
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core.UserSession
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.APIManager
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.service.FeedbacksService
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.service.LocationsService
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.utils.PLogger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_questionnaire.*

/**
 * Created by Imaduddin Al Fikri on 24/04/18.
 */

class QuestionnaireActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        imageSmile.setOnClickListener {
            sendFeedback(true)
        }

        imageSad.setOnClickListener {
            sendFeedback(false)
        }
    }

    override fun getCustomTitle(): String {
        return ""
    }

    override fun shouldShowBackButton(): Boolean {
        return false
    }

    override fun shouldHideBar(): Boolean {
        return true
    }

    private fun sendFeedback(isLike: Boolean) {
        val location = UserSession.selectedLocation

        if (location == null) return

        PLogger.show("send feedback ${isLike} - ${location.id}")

        APIManager.getService<FeedbacksService>()
                .postFeedback(isLike, location.id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            val feedback = it.data

                            if (feedback != null) {
                                PLogger.show("success feedback ${feedback.isLike} - ${feedback.locationID}")
                            }
                        },
                        {
                            PLogger.showError("error: ${it.message}")
                            PLogger.showError("error localized: ${it.localizedMessage}")
                        }
                )
    }
}
