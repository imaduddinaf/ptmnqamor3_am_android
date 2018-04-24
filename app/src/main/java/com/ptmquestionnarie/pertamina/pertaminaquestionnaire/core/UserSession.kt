package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core

import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.PLocation
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.APIManager
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.service.LocationsService
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.utils.PLogger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Imaduddin Al Fikri on 24/04/18.
 */

object UserSession {

    var locations: Array<PLocation> = arrayOf()
    var selectedLocation: PLocation? = null

    init {

    }

    fun getSelectedLocationPosition(): Int {
        if (selectedLocation == null) return -1

        val locationsID = locations.map { it.id }

        return locationsID.indexOf(selectedLocation!!.id)
    }

    fun locationsRequest(success: (Array<PLocation>) -> Unit) {
        APIManager.getService<LocationsService>()
                .getLocations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it.data != null) {
                                locations = it.data!!

                                if (selectedLocation == null) {
                                    selectedLocation = locations.first()
                                }

                                success(locations)
                            }
                        },
                        {
                            PLogger.showError("error: ${it.message}")
                            PLogger.showError("error localized: ${it.localizedMessage}")
                        }
                )
    }
}