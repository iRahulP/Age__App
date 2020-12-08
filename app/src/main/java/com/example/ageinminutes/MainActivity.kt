package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }


    fun clickDatePicker(view: View) {
        Calendar.getInstance()
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
//                Toast.makeText(this, "Date of Birth : "+selectedDate,Toast.LENGTH_LONG).show()

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateToMinutes = theDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val cal = Calendar.getInstance()
                val today = cal.get(Calendar.DAY_OF_MONTH)

                val currentDateToMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

                val year: Long = differenceInMinutes / 525600               //--365*24*60
                val months: Long = (differenceInMinutes - year*365*24*60) / (30*24*60)
                var dday = today - dayOfMonth
                if ( dday < 0){
                    dday = -1 * (dday)
                }


                Toast.makeText(this, "Date of Birth : "+selectedDate,Toast.LENGTH_LONG).show()

              //  val remHrs : Long = (differenceInMinutes - year*365*24*60 - months*30*24*60 - day*24*60) /24
                ageResult.text = "You're lifetime is ... \n\n"+year.toString()+" Years \n"+months.toString()+" Months \nand "+dday.toString()+" Days"
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }

}