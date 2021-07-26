package com.fsocity.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*



class signup : AppCompatActivity() {

    var name_value=""
    var uname_value=""
    var email_value=""
    var phone_value=""
    var department_value=""
    var password_value=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            var db=openOrCreateDatabase("rdrive", MODE_PRIVATE,null)
            setContentView(R.layout.activity_signup)
            var spit=findViewById<Spinner>(R.id.spinner)
            db.execSQL("CREATE TABLE IF NOT EXISTS user(uid INT,name TEXT,uname TEXT,email TEXT,phone TEXT,department TEXT,password TEXT);")
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
        }catch (e:Exception){
Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show()
        }
        var name=findViewById<LinearLayout>(R.id.name)
        var email=findViewById<LinearLayout>(R.id.email)
        var department=findViewById<LinearLayout>(R.id.department)
        var password=findViewById<LinearLayout>(R.id.password)
        var phone=findViewById<LinearLayout>(R.id.phone)
        email.visibility= View.INVISIBLE
        department.visibility= View.INVISIBLE
        password.visibility= View.INVISIBLE
        phone.visibility= View.INVISIBLE
        name.visibility= View.VISIBLE
    }

    fun renderName(v:View){
        var name=findViewById<LinearLayout>(R.id.name)
        var email=findViewById<LinearLayout>(R.id.email)
        var department=findViewById<LinearLayout>(R.id.department)
        var password=findViewById<LinearLayout>(R.id.password)
        var phone=findViewById<LinearLayout>(R.id.phone)
        email.visibility=View.INVISIBLE
        department.visibility=View.INVISIBLE
        password.visibility=View.INVISIBLE
        phone.visibility=View.INVISIBLE
        name.visibility=View.VISIBLE
    }
    fun renderemail(v:View){
        var name=findViewById<LinearLayout>(R.id.name)
        var email=findViewById<LinearLayout>(R.id.email)
        var department=findViewById<LinearLayout>(R.id.department)
        var password=findViewById<LinearLayout>(R.id.password)
        var phone=findViewById<LinearLayout>(R.id.phone)
        email.visibility=View.VISIBLE
        department.visibility=View.INVISIBLE
        password.visibility=View.INVISIBLE
        phone.visibility=View.INVISIBLE
        name.visibility=View.INVISIBLE
    }
    fun renderdepartment(v:View){
        var name=findViewById<LinearLayout>(R.id.name)
        var email=findViewById<LinearLayout>(R.id.email)
        var department=findViewById<LinearLayout>(R.id.department)
        var password=findViewById<LinearLayout>(R.id.password)
        var phone=findViewById<LinearLayout>(R.id.phone)
        email.visibility=View.INVISIBLE
        department.visibility=View.VISIBLE
        password.visibility=View.INVISIBLE
        phone.visibility=View.INVISIBLE
        name.visibility=View.INVISIBLE
    }
    fun rendePassword(v:View){
        var name=findViewById<LinearLayout>(R.id.name)
        var email=findViewById<LinearLayout>(R.id.email)
        var department=findViewById<LinearLayout>(R.id.department)
        var password=findViewById<LinearLayout>(R.id.password)
        var phone=findViewById<LinearLayout>(R.id.phone)
        email.visibility=View.INVISIBLE
        department.visibility=View.INVISIBLE
        password.visibility=View.VISIBLE
        phone.visibility=View.INVISIBLE
        name.visibility=View.INVISIBLE
    }
    fun rendephone(v:View){
        var name=findViewById<LinearLayout>(R.id.name)
        var email=findViewById<LinearLayout>(R.id.email)
        var department=findViewById<LinearLayout>(R.id.department)
        var password=findViewById<LinearLayout>(R.id.password)
        var phone=findViewById<LinearLayout>(R.id.phone)
        email.visibility=View.INVISIBLE
        department.visibility=View.INVISIBLE
        password.visibility=View.INVISIBLE
        phone.visibility=View.VISIBLE
        name.visibility=View.INVISIBLE
    }


    fun savename(v:View){
        var name=findViewById<EditText>(R.id.name_text)
        var uname=findViewById<EditText>(R.id.uname_text)
        var nameval=findViewById<TextView>(R.id.name_vali)
        var unameval=findViewById<TextView>(R.id.uname_valid)
        var db=openOrCreateDatabase("rdrive", MODE_PRIVATE,null)
        nameval.text=""
        unameval.text=""
        if(name.text.isEmpty()||uname.text.isEmpty()||uname.text.length<5){
            if(name.text.isEmpty())
                nameval.text="Enter name"
            if(uname.text.isEmpty())
                unameval.text="Enter User name"
            if(uname.text.length<5&&!uname.text.isEmpty())
                unameval.text="Username cannot be lesser than 5 characters"
        }else{
            var uname1=uname.text.toString()
            try {
                val result = db.rawQuery("SELECT * FROM user WHERE uname='$uname1'", null)
                if (result.count > 0)
                    unameval.text = "User name already taken"
                else {
                    name_value = name.text.toString()
                    uname_value = uname.text.toString()
                    renderemail(v)
                }
            }catch (e:Exception){
                Toast.makeText(this,"faild",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun backfromemail(v:View){
        renderName(v)
    }

    fun saveemail(v:View){
        var email=findViewById<EditText>(R.id.email_id)
        var emailval=findViewById<TextView>(R.id.email_valid)
        var db=openOrCreateDatabase("rdrive", MODE_PRIVATE,null)
        emailval.text=""
        var email1=email.text.toString()
        if(email.text.isEmpty()||android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches().toString()=="false"){
            if(email.text.isEmpty())
                emailval.text="Enter email ID"
            if(!email.text.isEmpty()&&android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches().toString()=="false")
                emailval.text="Enter Valid email ID"
        }else{
            try {
                val result = db.rawQuery("SELECT * FROM user WHERE email='$email1'", null)
                if (result.count > 0)
                    emailval.text = "Email id already taken"
                else {
                    email_value = email.text.toString()
                    rendephone(v)
                }
            }catch (e:Exception){
                Toast.makeText(this,"faild",Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun backfromphone(v:View){
        renderemail(v)
    }

    fun savephone(v:View){
        var phoneno=findViewById<EditText>(R.id.phone_text)
        var phone_otp=findViewById<EditText>(R.id.otp_phone)
        var phoneval=findViewById<TextView>(R.id.phone_valid)
        var otpval=findViewById<TextView>(R.id.otp_valid_phone)
        var db=openOrCreateDatabase("rdrive", MODE_PRIVATE,null)
        phoneval.text=""
        otpval.text=""
        var phone1=phoneno.text.toString()
        if(phoneno.text.isEmpty()||phone_otp.text.isEmpty()||phone1.length!=10){
            if(phoneno.text.isEmpty())
                phoneval.text="Enter Phone Number"
            if(phone_otp.text.isEmpty())
                otpval.text="Enter OTP"
            if(!phoneno.text.isEmpty()&&phone1.length!=10)
                phoneval.text="Enter Valid Phone Number"
        }else{
            try {
                val result = db.rawQuery("SELECT * FROM user WHERE phone='$phone1'", null)
                if (result.count > 0)
                    phoneval.text = "Phone number already taken"
                else {
                    phone_value = phoneno.text.toString()

                    renderdepartment(v)
                }
            }catch (e:Exception){
                Toast.makeText(this,"faild",Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun backfromdepartment(v:View){
        rendephone(v)
    }

    fun savedepartment(v:View){
        department_value=department_value
        rendePassword(v)
    }
    fun backfrompassword(v:View){
        renderdepartment(v)
    }
    fun create(v:View) {
        var password = findViewById<EditText>(R.id.password_text)
        var cpassword = findViewById<EditText>(R.id.confirm_password)
        var passval = findViewById<TextView>(R.id.password_valid)
        var conformpassval = findViewById<TextView>(R.id.confirmpassword_valid)
        if (password.text.isEmpty() || cpassword.text.isEmpty()) {
            if (password.text.isEmpty())
                passval.text = "Enter Password"
            if (cpassword.text.isEmpty())
                conformpassval.text = "Enter Confirm Password"
        } else {
            if (password.text.length > 8) {
                if (password.text.toString() == cpassword.text.toString()) {
                    var db = openOrCreateDatabase("rdrive", MODE_PRIVATE, null)
                    var id = 0
                    val result = db.rawQuery("SELECT * FROM user", null)
                    if (result.moveToLast()) {
                        id = result.getString(0).toInt() + 1
                    }
                    password_value=password.text.toString()
                    db.execSQL("INSERT INTO user VALUES('$id','$name_value','$uname_value','$email_value','$phone_value','$department_value','$password_value');");
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                } else {
                    conformpassval.text = "Password didnt match."
                }
            } else {
                passval.text = "Password should be more then 8 character"
            }
        }
    }
}