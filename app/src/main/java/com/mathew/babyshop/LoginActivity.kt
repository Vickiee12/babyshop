package com.mathew.babyshop

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth
    private  lateinit var etloginemail:TextInputEditText
    private  lateinit var etloginPassword:TextInputEditText
    private  lateinit var btnlogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etloginemail = findViewById(R.id.etloginemail)
        etloginPassword = findViewById(R.id.etloginPassword)
        btnlogin = findViewById(R.id.btnlogin)
        mAuth=FirebaseAuth.getInstance()
        btnlogin.setOnClickListener{
            val email = etloginemail.text.toString().trim()
            val password = etloginPassword.text.toString().trim()
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                btnlogin.setText("Signing in..")
//                data is ready to be validated
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { res->
                    if (res.isSuccessful){
//                        here user is successfully logged in, so we move to home
                        startActivity(Intent(this,Activity::class.java))
                        finish()
                    }else{
                        btnlogin.setText("Try again")
                        Toast.makeText(this, "something went wrong: ${res.exception?.message}",
                            Toast.LENGTH_SHORT)
                            .show()
                    }

                }


            }else{
                Toast.makeText(this, "Check your email or password", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun moveToRegister(view: View) {
        startActivity(Intent(this,RegisterActivity::class.java))
        finish()
    }
}