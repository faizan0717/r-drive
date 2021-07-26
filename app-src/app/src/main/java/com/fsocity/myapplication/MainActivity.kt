package com.fsocity.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onlogin(v: View) {
        var uname = findViewById<EditText>(R.id.login_uname) as EditText
        var pass1 = findViewById<EditText>(R.id.login_password) as EditText
        var unameval = findViewById<TextView>(R.id.logiin_uname_confirm)
        var passval = findViewById<TextView>(R.id.logiin_password_confirm)
        val username = uname.text.toString()
        var pass = pass1.text.toString()

        if (username == "admin" && pass == "admin") {
            intent = Intent(this, admin_home::class.java)
            startActivity(intent)
        } else {
            unameval.text = ""
            passval.text = ""
            try {
                if (uname.text.isEmpty() || pass1.text.isEmpty()) {
                    if (uname.text.isEmpty())
                        unameval.text = "Enter Username"
                    if (pass1.text.isEmpty())
                        passval.text = "Enter password"
                } else {
                    var db = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)

                    val result = db.rawQuery(
                        "SELECT * FROM user WHERE uname='$username' and password='$pass'",
                        null
                    )
                    if (result.moveToFirst()) {
                        val tempid=result.getString(0).toString()

                        intent = Intent(this, user_home::class.java)
                        intent.putExtra("uid",tempid)
                        startActivity(intent)
                    } else {
                        passval.text = "Invalid email/password"
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
            }
        }
    }




    fun oncreate(v: View){
        val i = Intent(this, signup::class.java)
        startActivity(i)
    }
}