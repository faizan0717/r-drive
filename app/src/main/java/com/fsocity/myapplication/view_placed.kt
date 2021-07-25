package com.fsocity.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class view_placed : AppCompatActivity() {
    var final = ""
    var department_value = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_placed)
        var spit = findViewById<Spinner>(R.id.register_select)
        val BranchNames = arrayOf("CSE ","ISE","ECE","EEE","CIVIL","MECH")
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,BranchNames)

        spit.adapter = arrayAdapter
        spit.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1:View?, p2:Int,p3: Long)
            {
                val final = BranchNames[p2]
                department_value=final.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                department_value="CSE"
            }
        }
    }

    fun onSearch(v: View) {
        var db = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)

        val parent = findViewById<LinearLayout>(R.id.parentview)
        parent.removeAllViews()

        val result = db.rawQuery("SELECT * from user where department='$department_value'", null)
        if (result.moveToFirst()) {
            do {
                var uid = result.getString(0).toString()
                //Toast.makeText(this, pid, Toast.LENGTH_SHORT).show()
                val result2 = db.rawQuery(
                    "SELECT u.name,r.company_name from register p,user u,placement r where r.pid=p.pid and p.placed='1' and u.uid=p.uid",
                    null
                )
                if (result2.moveToFirst()) {
                    do {
                        //Toast.makeText(this,result2.count.toString(), Toast.LENGTH_SHORT).show()

                        val child = LinearLayout(this)
                        child.orientation = LinearLayout.VERTICAL
                        child.setBackgroundColor(Color.parseColor("#709C27B0"))
                        val company = TextView(this)
                        company.text = result2.getString(0).toString()
                        company.textSize = 34.0F
                        //company.setTextColor(Color.parseColor("#A14141"))
                        company.width = LinearLayout.LayoutParams.MATCH_PARENT
                        company.setBackgroundColor(Color.parseColor("#FFA14141"))

                        val date = TextView(this)
                        date.text = "Company:" + result2.getString(1).toString()
                        date.setTextColor(Color.parseColor("#FFA14141"))
                        date.textSize = 24.0F

                        val temp = TextView(this)



                        child.addView(company)

                        child.addView(date)
                        parent.addView(temp)
                        parent.addView(child)
                    } while (result2.moveToNext())
                }else{

                }
            } while (result.moveToNext())
        }
    }
}