package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.utils.Constant
import java.util.ArrayList

/**
 * Created by Imaduddin Al Fikri on 24/04/18.
 */

open abstract class BaseFragment : Fragment() {

    val activityHolder: BaseActivity
        get() = this.activity as BaseActivity

    val customTitle: String
        get() = activityHolder.getCustomTitle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityHolder.setTitle(customTitle)
    }
}