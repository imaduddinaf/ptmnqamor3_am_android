package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.utils.Constant

/**
 * Created by Imaduddin Al Fikri on 24/04/18.
 */

open abstract class BaseActivity : AppCompatActivity() {

    private var customTitle: String = Constant.TITLE

    fun getCustomTitle(): String {
        return customTitle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = customTitle

        if (shouldShowBackButton()) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeButtonEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home && shouldShowBackButton()) finish()

        return super.onOptionsItemSelected(item)
    }

    open fun shouldShowBackButton(): Boolean {
        return true
    }
}