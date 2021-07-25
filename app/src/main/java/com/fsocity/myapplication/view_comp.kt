package com.fsocity.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

class view_comp : AppCompatActivity() {
    var id=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_comp)
        try {
            val db = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)
            val result = db.rawQuery(
                "SELECT * FROM placement",
                null
            )
            if (result.moveToFirst()) {
                val parent = findViewById<LinearLayout>(R.id.parentview)
                do {


                    val child = LinearLayout(this)
                    child.orientation = LinearLayout.VERTICAL
                    child.setBackgroundColor(Color.parseColor("#709C27B0"))


                    val company = TextView(this)
                    company.text = "Company:" + result.getString(1).toString()
                    company.textSize = 34.0F
                    //company.setTextColor(Color.parseColor("#A14141"))
                    company.width = LinearLayout.LayoutParams.MATCH_PARENT
                    company.setBackgroundColor(Color.parseColor("#FFA14141"))

                    val date = TextView(this)
                    date.text = "Date:" + result.getString(4).toString()
                    date.setTextColor(Color.parseColor("#FFA14141"))
                    date.textSize = 24.0F

                    val location = TextView(this)
                    location.text = "Location:" + result.getString(2).toString()
                    location.setTextColor(Color.parseColor("#FFA14141"))
                    location.textSize = 24.0F
                    val temp = TextView(this)

                    val buttonchild = LinearLayout(this)
                    val button1 = Button(this)
                    val button2 = Button(this)
                    val button3 = Button(this)

                    button1.text = "DELETE"
                    var pid=result.getString(0).toInt()
                    button1.id=result.getString(0).toInt()
                    button1.setOnClickListener {
                        try{
                            val database = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)
                            db.execSQL("CREATE TABLE IF NOT EXISTS register(uid INT,pid INT,registerd TEXT,placed TEXT);")
                            val id=button1.getId().toString()
                            database.execSQL("DELETE FROM register WHERE pid='$id'")
                            database.execSQL("DELETE FROM placement WHERE pid='$id'")
                            val i= Intent(this,view_comp::class.java)
                            finish();
                            startActivity(i)
                        }catch (e:Exception){
                            Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
                        }
                    }
                    button2.text="Update List"
                    button2.id=result.getString(0).toInt()
                    button2.setOnClickListener {
                        try{
                            val vie=findViewById<ConstraintLayout>(R.id.bbb)
                            vie.visibility=View.VISIBLE
                            vie.bringToFront()
                            id=button1.getId().toString()

                        }catch (e:Exception){
                            Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
                        }
                    }

                    buttonchild.addView(button1)
                    buttonchild.addView(button2)
                    child.addView(company)
                    child.addView(date)
                    child.addView(location)
                    child.addView(buttonchild)
                    parent.addView(temp)
                    parent.addView(child)

                }while (result.moveToNext());
            }else{
                var nocampus=findViewById<TextView>(R.id.textView)
                nocampus.visibility= View.VISIBLE
            }
/*
            val button = Button(this)
            val button2 = Button(this)
                    button.setText("DEL")
                    button.id="1".toInt()//result.getString(0).toInt()
                    child.setPadding(0,10,0,0)
            child.setOrientation(LinearLayout.VERTICAL);
                    button.setBackgroundColor(Color.parseColor("#FFFF0000"))
                    button.setOnClickListener {
                        try{
                            val database = openOrCreateDatabase("todo", MODE_PRIVATE, null)
                            val id=button.getId().toString()
                            database.execSQL("DELETE FROM userdata WHERE id='$id'")
                            //val i= Intent(this,home::class.java)
                            //i.putExtra("uname",uname)
                           // finish();
                            //startActivity(i)
                        }catch (e:Exception){
                            Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
                        }
                    }
                    button.width=102
                    button.height=56
                    text.minWidth=306*3
                    text.maxWidth=306*3
                    text.minHeight=120
                    text.setBackgroundColor(Color.parseColor("#59FCFDFB"))
                    text.setTextColor(Color.parseColor("#000000"))
                    text.text ="casacscascsac"// result.getString(2)
                    child.addView(text)
            child.addView(button)
            child.addView(button2)

                    parent.addView(child)
               // } while (result.moveToNext())
*/
        } catch (e: Exception) {
            Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
        }
    }

    fun cancel(v:View){
        val i= Intent(this,view_comp::class.java)
        finish();
        startActivity(i)
    }

    fun addURL(v:View){
        val database = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)
        val urltext=findViewById<EditText>(R.id.editTextTextPersonName)
        var url=urltext.text.toString()
        //database.execSQL("CREATE TABLE IF NOT EXISTS register(uid INT,pid INT,registerd TEXT,placed TEXT);")
        database.execSQL("UPDATE placement SET doc='$url' where pid='$id'")
        val i= Intent(this,view_comp::class.java)
        finish();
        startActivity(i)
    }
}