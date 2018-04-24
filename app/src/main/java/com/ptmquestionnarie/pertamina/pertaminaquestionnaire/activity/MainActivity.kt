package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.FragmentManager
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.R
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core.BaseActivity
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core.BaseFragment
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core.UserSession
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.fragment.HomeFragment
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.fragment.SettingsFragment
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.utils.kClass
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : BaseActivity() {

    private var fragment: BaseFragment? = null
    private var fragmentManager: FragmentManager? = null

    private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                if (fragment!!.kClass.qualifiedName === HomeFragment::class.qualifiedName) {
                    return@OnNavigationItemSelectedListener true
                }

                fragment = HomeFragment()
            }
            R.id.navigation_settings -> {
                if (fragment!!.kClass.qualifiedName === SettingsFragment::class.qualifiedName) {
                    return@OnNavigationItemSelectedListener true
                }

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

        UserSession.locationsRequest {

        }
    }

    override fun shouldShowBackButton(): Boolean {
        return false
    }
}
