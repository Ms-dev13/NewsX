package com.example.newsx

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList


class Category : AppCompatActivity() {
    private val cataItem =  ArrayList<Datainfo>();
    private val selectedItems = mutableSetOf<Int>()
    private lateinit var submitbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_category)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclemain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView:RecyclerView = findViewById(R.id.recyclemain)

        createInstance()
        submitbtn = findViewById(R.id.btnSubmit)
        submitbtn.isEnabled = false

        val adapter = CustomeAdapter(this,cataItem,selectedItems){
                isSelected ->
            // Enable or disable the Submit button based on selection status
            submitbtn.isEnabled = isSelected
        }

        recyclerView.adapter = adapter;

        recyclerView.layoutManager = LinearLayoutManager(this);

        submitbtn.setOnClickListener(){
            val selectedItems = adapter.getSelectedItems()
            if (selectedItems.isNotEmpty()) {
                val selectedNames = selectedItems.joinToString { it.text }
                Toast.makeText(this, "Selected: $selectedNames", Toast.LENGTH_SHORT).show()
            }

        }


    }

      fun createInstance(){

        val cat_name: Array<String> = arrayOf("MotorSport","Finance","Technology","Entertainment","Health","Government");

        val cat_image: Array<Int> = arrayOf(R.drawable.f1_2418802,R.drawable.finance,R.drawable.technology,R.drawable.movies,
            R.drawable.medical_team,R.drawable.government
            )

      val bg_color: Array<Int> = arrayOf( ContextCompat.getColor( this, R.color.Card_bg1),
                ContextCompat.getColor(this, R.color.Card_bg_2),
                ContextCompat.getColor(this, R.color.Card_bg3),
                ContextCompat.getColor(this, R.color.Card_bg4),
                ContextCompat.getColor(this, R.color.Card_bg5),
                ContextCompat.getColor(this, R.color.Card_bg6)

                )





        for( index in cat_name.indices){
           // Inserting DataItem object in arrayList which will be passed to adapter to retrieve and apply the data in viewHolder

            cataItem.add(Datainfo( index,
                cat_name[index],
                cat_image[index],
                bg_color[index] )
            )

        }

    }
}