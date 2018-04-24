package com.ptmquestionnarie.pertamina.pertaminaquestionnaire.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.R
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.activity.QuestionnaireActivity
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core.BaseFragment
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.core.UserSession
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.PLocation
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.model.User
import com.ptmquestionnarie.pertamina.pertaminaquestionnaire.utils.PLogger
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Imaduddin Al Fikri on 24/04/18.
 */

class HomeFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

    private var locations: Array<PLocation> = arrayOf()

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home,
                container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dropdownLocation.onItemSelectedListener = this

        if (UserSession.locations.isEmpty()) {
            UserSession.locationsRequest {
                this.locations = it
                this.configureDropdown()
            }
        } else {
            locations = UserSession.locations
            configureDropdown()
        }

        buttonQuestionnaire.setOnClickListener {
            intentQuestionnaireActivity()
        }
    }

    private fun configureDropdown() {
        val locationsName = locations.map { it.name }
        val arrayAdapter = ArrayAdapter(this.context, android.R.layout.simple_spinner_item, locationsName)

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        dropdownLocation.adapter = arrayAdapter

        if (UserSession.getSelectedLocationPosition() > 0) {
            dropdownLocation.setSelection(UserSession.getSelectedLocationPosition())
        }

    }

    private fun intentQuestionnaireActivity() {
        val intent = Intent(this.activity, QuestionnaireActivity::class.java)
        activity.startActivity(intent)
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        UserSession.selectedLocation = locations[position]
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
}
