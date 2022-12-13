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

        val cal = CalendarView(this) // USTAWIENIE ZMIENNEJ KALENDARZA
        cal.setDate(Calendar.getInstance().getTimeInMillis(),false,true); // POBRANIE CZASU W MILISEKUNDACH

        val formatter = SimpleDateFormat("dd/MM/yyyy") // USTAWIENIE FORMATU DATY

        var departure_date = 0L
        var date_of_return = 0L

        findViewById<TextView>(R.id.textView_wyjazd).text = formatter.format(findViewById<CalendarView>(R.id.kalendarz).date).toString() // TEXTVIEW OTRZYMUJE WARTOSC WYJAZDU (DEFAULT: DZISIEJSZA DATA)
        findViewById<TextView>(R.id.textView_powrot).text = formatter.format(findViewById<CalendarView>(R.id.kalendarz).date).toString() // TEXTVIEW OTRZYMUJE WARTOSC POWROTU (DEFAULT: DZISIEJSZA DATA)

        findViewById<CalendarView>(R.id.kalendarz).minDate = findViewById<CalendarView>(R.id.kalendarz).date // USTAWIENIE MIN. DATY NA AKTUALNY DZIEŃ
        findViewById<CalendarView>(R.id.kalendarz).maxDate = findViewById<CalendarView>(R.id.kalendarz).date + 63113852000 // USTAWIENIE MAKS. DATY O 2 LATA DO PRZODU

        findViewById<Button>(R.id.btn_przyjazd).setOnClickListener { // USTAWIENIE DATY PRZYJAZDU
            val a = findViewById<CalendarView>(R.id.kalendarz).date

            if (date_of_return - a < 0)
            {
                findViewById<TextView>(R.id.textView_ERROR).text = "Data wyjazdu jest przed datą przyjazdu! Błąd!"
            }

            else
            {
                departure_date = a
                findViewById<TextView>(R.id.textView_ERROR).text = ""
                findViewById<TextView>(R.id.textView_wyjazd).text = formatter.format(findViewById<CalendarView>(R.id.kalendarz).date).toString()
            }
        }

        val a = findViewById<CalendarView>(R.id.kalendarz)

        a.setOnDateChangeListener { calendarView, i, i2, i3 ->
            a.date = Date.UTC(i - 1900, i2, i3, 4, 4, 0)
        }

        findViewById<Button>(R.id.btn_powrot).setOnClickListener { // USTAWIENIE DATY POWROTU
            val a = findViewById<CalendarView>(R.id.kalendarz).date

            if (a - departure_date >= 0)  // ZABEZPIECZENIE: GDY DATA POWROTU JEST STARSZA OD DATY PRZYJAZDU WYRZUCA BŁĄD
            {
                date_of_return = a
                findViewById<TextView>(R.id.textView_ERROR).text = ""
                findViewById<TextView>(R.id.textView_powrot).text = formatter.format(findViewById<CalendarView>(R.id.kalendarz).date).toString()
            }
            else
            {
                findViewById<TextView>(R.id.textView_ERROR).text = "Data wyjazdu jest przed datą przyjazdu! Błąd!"
            }
        }

        findViewById<Button>(R.id.btn_count).setOnClickListener { // OBLICZANIE RÓŻNICY

            if (departure_date != 0L && date_of_return != 0L)
            {
                if (departure_date <= date_of_return) // ZABEZPIECZENIE: GDY DATA POWROTU JEST STARSZA OD DATY PRZYJAZDU WYRZUCA BŁĄD I NIE OBLICZA RÓŻNICY
                {
                    findViewById<TextView>(R.id.textView_ERROR).text = ""

                    val result = (date_of_return - departure_date) / 86400000 // OBLICZANIE DŁUGOŚCI WYJAZDU, ZMIANA MILISEKUND NA DNI
                    findViewById<TextView>(R.id.textView_ERROR).text = "Wyjazd trwa " + result + " dni"

                }

                else
                {
                    findViewById<TextView>(R.id.textView_ERROR).text = "Data wyjazdu jest przed datą przyjazdu! Błąd!"
                }
            }

            else
            {
                findViewById<TextView>(R.id.textView_ERROR).text = "Wyjazd trwa 0 dni"
            }
        }
    }
}