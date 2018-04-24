package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.R
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core.BaseActivity
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core.BaseFragment
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.fragment.HomeFragment
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.fragment.SettingsFragment
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.APIManager
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.network.service.LocationsService
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.utils.PLogger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : BaseActivity() {

    var disposable: Disposable? = null

    private var fragment: BaseFragment? = null
    private var fragmentManager: FragmentManager? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        request()

        when (item.itemId) {
            R.id.navigation_home -> {
//                if (fragment.getClass() === HomeFragment_::class.java) return true

                PLogger.show("home tab")
                fragment = HomeFragment()
            }
            R.id.navigation_settings -> {
//                if (fragment.getClass() === ProfileFragment_::class.java) return true

                PLogger.show("settings tab")
                fragment = SettingsFragment()
            }
        }
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.content, fragment)?.commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment = HomeFragment()
        fragmentManager = supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.content, fragment)?.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun shouldShowBackButton(): Boolean {
        return false
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
