package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.R
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.APIManager
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.service.LocationsService
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.utils.PLogger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var disposable: Disposable? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        request()

        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun request() {
        disposable = APIManager.getService<LocationsService>()
                .getLocations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            val locations = result.data

                            if (locations != null) {
                                PLogger.showSuccess("result total: " + locations.size.toString())
                                PLogger.showSuccess("first location: " + locations.first().name)
                            } else {
                                PLogger.showSuccess("empty locations")
                            }
                        },
                        { error ->
                            PLogger.showError("error: " + error.message)
                            PLogger.showError("error loc: " + error.localizedMessage)
                        }
                )
    }
}
