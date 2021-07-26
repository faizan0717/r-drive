package com.fsocity.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_admin_home.*

class admin_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
    }
    fun addComp(v:View){
        intent = Intent(this, com.fsocity.myapplication.add_comp::class.java)
        startActivity(intent)
    }

    fun viewComp(v:View){
        intent = Intent(this, com.fsocity.myapplication.view_comp::class.java)
        startActivity(intent)
    }

    fun viewreg(v:View){
        intent = Intent(this, com.fsocity.myapplication.viewregister::class.java)
        startActivity(intent)
    }
    fun viewplaced(v:View){
        intent = Intent(this, com.fsocity.myapplication.view_placed::class.java)
        startActivity(intent)
    }
}