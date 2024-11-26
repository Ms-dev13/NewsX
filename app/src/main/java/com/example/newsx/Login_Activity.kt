package com.example.newsx

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        val Email_text = findViewById<EditText>(R.id.editTextText2)
        val Pass_text = findViewById<EditText>(R.id.editTextTextPassword)
        val loginbtn = findViewById<Button>(R.id.button);


        loginbtn.setOnClickListener(){
            val email = Email_text.text.toString();
            val pass = Pass_text.text.toString();

            if(email.isNotEmpty() && pass.isNotEmpty()){

                if(email == "manthan1309@gmail.com" && pass =="password"){
                    Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();



                    val intent = Intent(this,MainActivity::class.java);
                    startActivity(intent);

                    Log.i("Login_Activity","After Intent call")
                }
                else {
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }

            }
            else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
        }


    }
}