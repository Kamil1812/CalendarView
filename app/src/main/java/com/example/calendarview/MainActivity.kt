package com.example.calendarview

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cal = CalendarView(this)
        cal.setDate(Calendar.getInstance().getTimeInMillis(),false,true);

        var departure_date = findViewById<CalendarView>(R.id.kalendarz).date // DATA PRZYJAZDU
        var date_of_return = findViewById<CalendarView>(R.id.kalendarz).date // DATA POWROTU

        findViewById<Button>(R.id.btn_przyjazd).setOnClickListener {
            departure_date = findViewById<CalendarView>(R.id.kalendarz).date
        }

        findViewById<Button>(R.id.btn_powrot).setOnClickListener {

            if (departure_date <= date_of_return)
            {

            }

            else
            {

            }
        }
    }
}