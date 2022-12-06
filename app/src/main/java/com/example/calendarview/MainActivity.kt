package com.example.calendarview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cal = CalendarView(this)
        cal.setDate(Calendar.getInstance().getTimeInMillis(),false,true);

        var departure_date = 0L // DATA PRZYJAZDU
        var date_of_return = 0L // DATA POWROTU

        val formatter = SimpleDateFormat("dd/MM/yyyy")

        findViewById<TextView>(R.id.textView_wyjazd).text = formatter.format(findViewById<CalendarView>(R.id.kalendarz).date).toString()
        findViewById<TextView>(R.id.textView_powrot).text = formatter.format(findViewById<CalendarView>(R.id.kalendarz).date).toString()

        findViewById<CalendarView>(R.id.kalendarz).minDate = findViewById<CalendarView>(R.id.kalendarz).date
        findViewById<CalendarView>(R.id.kalendarz).maxDate = findViewById<CalendarView>(R.id.kalendarz).date + 63113852000

        findViewById<Button>(R.id.btn_przyjazd).setOnClickListener {
            departure_date = findViewById<CalendarView>(R.id.kalendarz).date
            findViewById<TextView>(R.id.textView_wyjazd).text = formatter.format(findViewById<CalendarView>(R.id.kalendarz).date).toString()
        }

        val a = findViewById<CalendarView>(R.id.kalendarz)

        a.setOnDateChangeListener { calendarView, i, i2, i3 ->
            a.date = Date.UTC(i - 1900, i2, i3, 4, 4, 0)
        }

        findViewById<Button>(R.id.btn_powrot).setOnClickListener {
            val a = findViewById<CalendarView>(R.id.kalendarz).date

            if (a - departure_date > 0)
            {
                date_of_return = a
                findViewById<TextView>(R.id.textView_powrot).text = formatter.format(findViewById<CalendarView>(R.id.kalendarz).date).toString()
            }
            else
            {
                findViewById<TextView>(R.id.textView_ERROR).text = "Nie wybrałes daty lub różnica dat jest błędna"
            }
        }

        findViewById<Button>(R.id.btn_count).setOnClickListener {

            if (departure_date != 0L && date_of_return != 0L)
            {
                if (departure_date <= date_of_return)
                {

                }

                else
                {
                    findViewById<TextView>(R.id.textView_ERROR).text = "Nie wybrałes daty lub różnica dat jest błędna"
                }
            }
        }
    }
}