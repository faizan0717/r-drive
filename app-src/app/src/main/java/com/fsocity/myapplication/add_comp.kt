package com.fsocity.myapplication
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

import kotlinx.android.synthetic.main.activity_add_comp.*
import java.util.*

class add_comp : AppCompatActivity() {

    // Variables for Datepicker
    val mycalendar = Calendar.getInstance()
    val year = mycalendar.get((Calendar.YEAR))
    val month = mycalendar.get((Calendar.MONTH))
    val day = mycalendar.get((Calendar.DAY_OF_MONTH))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_comp)

        //Database Creation Begins
        try {
            var db=openOrCreateDatabase("rdrive", MODE_PRIVATE,null)

        }catch (e:Exception){
            Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show()
        }
        //Database Creation Ends

//Datepicker Beginning
        placement.setOnClickListener { view ->
            val dpd =  DatePickerDialog(this,
                { view, selectedyear, selectedmonth, selecteddayOfMonth ->
                    val selecteddate = "$selecteddayOfMonth/${selectedmonth+1 }/$selectedyear"
                    placement.setText(selecteddate)
                }, year, month, day)

            dpd.datePicker.setMinDate(Date().time)
            dpd.show()
        }
        //Datepicker End

// Spinner Begins
        val BranchNames = arrayOf("CSE ","ISE","ECE","EEE","CIVIL","MECH")
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,BranchNames)
        spit.adapter = arrayAdapter
        spit.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1:View?, p2:Int,p3: Long)
            {
                val final = BranchNames[p2]
                spinbruh.setText("$final")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        // Spinner Ends

        //Insertion Begins
        insert.setOnClickListener {

            var db = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)

            var company_name_value = name.text.toString()

            var placement_date = placement.text.toString()
            var location_value = locationdata.text.toString()
            var department_value = spinbruh.text.toString()
            if (company_name_value.isEmpty() || placement_date.isEmpty() || location_value.isEmpty() || department_value.isEmpty()) {
                Toast.makeText(this, "Fields Cannot be Empty", Toast.LENGTH_SHORT).show()
            } else {
                db.execSQL("CREATE TABLE IF NOT EXISTS placement(pid INTEGER PRIMARY KEY AUTOINCREMENT ,company_name TEXT,location TEXT,department TEXT,placement TEXT,doc TEXT);")

                var id = 0
                val result = db.rawQuery("SELECT * FROM placement", null)
                if (result.moveToLast()) {
                    id = result.getString(0).toInt() + 1
                }

                db.execSQL("INSERT INTO placement VALUES('$id','$company_name_value','$location_value','$department_value','$placement_date','null');");                Toast.makeText(this, "Insert Successful", Toast.LENGTH_SHORT).show()

            }
        }

        //Insertion Ends

    }

}