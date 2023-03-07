package com.amitku.yolofitness

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amit.readdb.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var  database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Add below line to hide status bar
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN

        // Use to hide The app upper bar
        supportActionBar?.hide()

        val signButton=findViewById<Button>(R.id.btnSignUp)
        val etName=findViewById<TextInputEditText>(R.id.etName)
        val etMail= findViewById<TextInputEditText>(R.id.etMail)
        val userid= findViewById<TextInputEditText>(R.id.etUserName)
        val userPassword = findViewById<TextInputEditText>(R.id.etPassword)

        signButton.setOnClickListener {

            val name= etName.text.toString()
            val mail= etMail.text.toString()
            val uniqueId= userid.text.toString()
            val password= userPassword.text.toString()

            val user = User(name,mail,password,uniqueId)
            database =FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener {
             etName.text?.clear()
                etMail.text?.clear()
                userid.text?.clear()
                userPassword.text?.clear()


                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

            }

        }

    }
}