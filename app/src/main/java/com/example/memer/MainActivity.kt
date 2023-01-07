package com.example.memer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var currentImageurl: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadmeme()
    }
    private fun loadmeme(){
        val queue = Volley.newRequestQueue(this)
        val url = " https://meme-api.com/gimme"

        val jsonObjectRequest = JsonObjectRequest (
            /* method = */ Request.Method.GET, /* url = */ url, /* jsonRequest = */ null,
            /* listener = */ { response ->
                currentImageurl = response.getString("url")

                Glide.with(this).load(currentImageurl).into(imageashish) },

            {
                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
            })

// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)
    }

    fun shareMeme(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT," Made With 💕 By Ashish Maley $currentImageurl")
        val chooser= Intent.createChooser(intent," Share this meme using...")
        startActivity(chooser)
    }
    fun nextext(view: View) {
        loadmeme()
    }
}