package com.example.memer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.memer.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var fireBaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fireBaseAuth = FirebaseAuth.getInstance()
        binding.text1.setOnClickListener {
            val intent = Intent(this, SignUp_activity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.editTextTextEmailAddress2.text.toString()
            val pass = binding.editTextNumberPassword.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                fireBaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            } else {
                Toast.makeText(this, "please fill both the required field", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if(fireBaseAuth.currentUser!=null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}