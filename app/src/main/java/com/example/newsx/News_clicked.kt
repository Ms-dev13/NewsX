package com.example.newsx

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class News_clicked : AppCompatActivity() {
   private lateinit var title:TextView
   private lateinit var image:ImageView
   private lateinit var discription:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_clicked)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val title1 = intent.getStringExtra("title") ?: "Default Title"
        val description1 = intent.getStringExtra("description") ?: "Default Description"
        val imageUrl = intent.getStringExtra("imageUrl") ?: ""
        title = findViewById(R.id.newstile)
        title.text = title1

        discription = findViewById(R.id.newsdiscription)
        discription.text = description1

        image = findViewById(R.id.newsimage)


        Glide.with(this)
            .load(imageUrl)
            .into(image)

    }
}