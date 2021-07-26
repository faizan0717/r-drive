package com.fsocity.myapplication

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class user_home : AppCompatActivity() {
    var uid=""
    var id=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)
        uid= intent.getStringExtra("uid").toString()
        //uid="0"
        //Toast.makeText(this,uid,Toast.LENGTH_SHORT).show()
        try {
            val db = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)
            var result = db.rawQuery(
                "SELECT department FROM user where uid=$uid",
                null
            )
            var departmentval=""
            if (result.moveToFirst()) {
                departmentval=result.getString(0).toString()
            }
            //Toast.makeText(this,departmentval,Toast.LENGTH_SHORT).show()
            departmentval=departmentval.toUpperCase()
            result = db.rawQuery(
                "SELECT * FROM placement where department='$departmentval'",
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
                  button1.text = "REGISTER"
              button1.setOnClickListener {
                  try{
                      val database = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)

                      val id=button1.getId().toString()
                      database.execSQL("INSERT INTO register values('$uid','$id','1','0')")
                      val i= Intent(this,user_home::class.java)
                      i.putExtra("uid",uid)
                      finish();
                      startActivity(i)
                  }catch (e:Exception){
                      Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
                  }
              }

                  val button2 = Button(this)
                  val button3 = Button(this)


              var pid=result.getString(0).toInt()
              button1.id=result.getString(0).toInt()




              button3.text = "Download List"
              button2.text = "Update Status"

              var db = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)
              db.execSQL("CREATE TABLE IF NOT EXISTS register(uid INT,pid INT,registerd TEXT,placed TEXT);")

              val result2 = db.rawQuery(
                  "SELECT * FROM register WHERE uid='$uid' and pid='$pid'",
                  null
              )

              buttonchild.addView(button1)
              if (result2.moveToFirst()) {
                  button1.text="Unregister"
                  button1.setOnClickListener {
                      try{
                          val database = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)
                          db.execSQL("CREATE TABLE IF NOT EXISTS register(uid INT,pid INT,registerd TEXT,placed TEXT);")
                          val id=button1.getId().toString()
                          database.execSQL("DELETE FROM register WHERE pid='$id' and uid='$uid'")
                          val i= Intent(this,user_home::class.java)
                          i.putExtra("uid",uid)
                          finish();
                          startActivity(i)
                      }catch (e:Exception){
                          Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
                      }
                  }
                  button2.id=result.getString(0).toInt()
                  button2.setOnClickListener {
                      try{
                          val vie=findViewById<ConstraintLayout>(R.id.aaaaaaaaaaaaaaa)
                          vie.visibility=View.VISIBLE
                          vie.bringToFront()
                          id=button1.getId().toString()
                      }catch (e:Exception){
                          Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
                      }
                  }
                  buttonchild.addView(button2)
              }
              if(result.getString(5).toString()!="null"){
                  //button3.id=result.getString(0).toInt()
                  var uri=result.getString(5).toString()
                  button3.setOnClickListener {
                      try{

                          //Toast.makeText(this,result.getString(5).toString(),Toast.LENGTH_SHORT).show()
                          var gourl= Intent(Intent.ACTION_VIEW,Uri.parse(uri))
                          startActivity(gourl)

                      }catch (e:Exception){
                          Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
                      }
                  }
                  buttonchild.addView(button3)
              }

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
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun selected(v:View){
        val db = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS register(uid INT,pid INT,registerd TEXT,placed TEXT);")
        db.execSQL("UPDATE register set placed='1' WHERE pid='$id' and uid='$uid'")
        val i= Intent(this,user_home::class.java)
        i.putExtra("uid",uid)
        finish();
        startActivity(i)
    }
    fun cancel(v:View){

        val i= Intent(this,user_home::class.java)
        i.putExtra("uid",uid)
        finish();
        startActivity(i)
    }
    fun notSelected(v:View){
        val database = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)
        database.execSQL("CREATE TABLE IF NOT EXISTS register(uid INT,pid INT,registerd TEXT,placed TEXT);")
        database.execSQL("DELETE FROM register WHERE pid='$id' and uid='$uid'")
        val i= Intent(this,user_home::class.java)
        i.putExtra("uid",uid)
        finish();
        startActivity(i)
    }
}