package com.fsocity.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class viewregister : AppCompatActivity() {
    var final = ""
    var compname = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewregister)
        setContentView(R.layout.activity_viewregister)
        var spit = findViewById<Spinner>(R.id.register_select)
        var db = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)
        val result = db.rawQuery("SELECT * from placement", null)
        if (result.moveToFirst()) {
            do {
                compname.add(result.getString(1).toString())
            } while (result.moveToNext())
        }
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, compname)
        spit.adapter = arrayAdapter
        spit.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                final = compname[p2].toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                final="TCS"
            }
        }
    }

    fun onSearch(v: View) {
        var db = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)

        val parent = findViewById<LinearLayout>(R.id.parentview)
        parent.removeAllViews()
        val result = db.rawQuery("SELECT * from placement where company_name='$final'", null)
        if (result.moveToFirst()) {
            do {
                var pid = result.getString(0).toString()
                //Toast.makeText(this, pid, Toast.LENGTH_SHORT).show()
                val result2 = db.rawQuery(
                    "SELECT u.name from register p,user u where p.pid='$pid' and u.uid=p.uid",
                    null
                )
                if (result2.moveToFirst()) {
                    do {
                        Toast.makeText(this,result2.count.toString(),Toast.LENGTH_SHORT).show()

                        val child = LinearLayout(this)
                        child.orientation = LinearLayout.VERTICAL
                        child.setBackgroundColor(Color.parseColor("#709C27B0"))
                        val company = TextView(this)
                        company.text = result2.getString(0).toString()
                        company.textSize = 34.0F
                        //company.setTextColor(Color.parseColor("#A14141"))
                        company.width = LinearLayout.LayoutParams.MATCH_PARENT
                        company.setBackgroundColor(Color.parseColor("#FFA14141"))
                        child.addView(company)
                        parent.addView(child)
                    } while (result2.moveToNext())
                }else{

                }
            } while (result.moveToNext())
        }
    }
}