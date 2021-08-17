package com.mathew.babyshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var etmail:   EditText
    private lateinit var etPassword:   EditText
    private lateinit var    etRetypePassword: EditText
    private  lateinit var btsignup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        etmail = findViewById(R.id.etemail)
        etPassword = findViewById(R.id.etPassword)
        etRetypePassword = findViewById(R.id.etRetypePassword)
        btsignup = findViewById(R.id.btsignup)
//        coonecting to the database
        mAuth = FirebaseAuth.getInstance()
//        when clicking the information inputed is to submited
        btsignup.setOnClickListener {
            val email = etmail.text.toString().trim()
            val pass1 = etPassword.text.toString().trim()
            val pass2 = etRetypePassword.text.toString().trim()

//setting some ruls to the input provided
            if (!TextUtils.isEmpty(email)){
                if (!TextUtils.isEmpty(pass1) && !TextUtils.isEmpty(pass2)){
//everythinis done
//    starting process of registration

                    if (pass1.equals(pass2))
                    {
                        btsignup.text ="creating account"
                        mAuth.createUserWithEmailAndPassword(email,pass1).addOnCompleteListener{ res->
//                        check if registration process was succesfullydone
                            if (res.isSuccessful){
                                btsignup.text ="Account created successfully"
                            }else{
                                btsignup.text ="something went wrong"
                                Toast.makeText(this, "Error: ${res.exception?.message}",
                                    Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }


                    }else{
                        Toast.makeText(this, "Passwords dont match", Toast.LENGTH_SHORT).show()

                    }
                }else{
                    Toast.makeText(this, "please enter  a valid password", Toast.LENGTH_SHORT).show()

                }

            } else {
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show()

            }
        }




    }
}