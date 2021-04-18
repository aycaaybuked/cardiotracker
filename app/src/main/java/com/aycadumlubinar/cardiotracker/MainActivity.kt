package com.aycadumlubinar.cardiotracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.aycadumlubinar.cardiotracker.utils.convertMinutesToSeconds
import com.aycadumlubinar.cardiotracker.utils.getTimeFromStr
import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.View.OnLongClickListener
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView



const val FORMAT = "%02d"
const val INTENT_SET_NUMBER = "sets_number"
const val INTENT_WORK_INTERVAL = "work_timer"
const val INTENT_REST_INTERVAL = "rest_timer"

class MainActivity : AppCompatActivity() {

    private lateinit var setNumberTV: TextView
    private lateinit var setNumberMinusB: ImageButton
    private lateinit var setNumberPlusB: ImageButton

    private lateinit var workIntervalTV: TextView
    private lateinit var workIntervalMinusB: ImageButton
    private lateinit var workIntervalPlusB: ImageButton

    private lateinit var restIntervalTV: TextView
    private lateinit var restIntervalMinusB: ImageButton
    private lateinit var restIntervalPlusB: ImageButton

    private lateinit var startB: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setNumberTV = a_main_text_view_sets_number
        setNumberMinusB = a_main_image_button_sets_minus
        setNumberPlusB = a_main_image_button_sets_plus
        workIntervalTV = a_main_text_view_work_timer
        workIntervalMinusB = a_main_image_button_work_timer_minus
        workIntervalPlusB = a_main_image_button_work_timer_plus
        restIntervalTV = a_main_text_view_rest_timer
        restIntervalMinusB = a_main_image_button_rest_timer_minus
        restIntervalPlusB = a_main_image_button_rest_timer_plus
        startB = a_main_button_start
        startB.setOnClickListener {
            val setNumber: Int = setNumberTV.text.toString().toInt()
            val workSeconds: Int = convertMinutesToSeconds(
                    getTimeFromStr(workIntervalTV.text.toString()).first,
                    getTimeFromStr(workIntervalTV.text.toString()).second
            )
            val restSeconds: Int = convertMinutesToSeconds(
                    getTimeFromStr(restIntervalTV.text.toString()).first,
                    getTimeFromStr(restIntervalTV.text.toString()).second
            )
            val intent = Intent(this, TimerActivity::class.java)
            intent.putExtra(INTENT_SET_NUMBER, setNumber)
            intent.putExtra(INTENT_WORK_INTERVAL, workSeconds)
            intent.putExtra(INTENT_REST_INTERVAL, restSeconds)
            this.startActivity(intent)
        }

    }

}