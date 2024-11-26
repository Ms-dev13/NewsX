package com.example.newsx

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var btnavigation:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnavigation = findViewById(R.id.bottomNavigationView);
        val fragmentmanaget:FragmentManager = supportFragmentManager
        val fragmentransaction:FragmentTransaction = fragmentmanaget.beginTransaction()
        fragmentransaction.add(R.id.container,HomeFragment())
       /* fragmentransaction.add(R.id.container,sportsFragment())
        fragmentransaction.add(R.id.container,ItFragment())
        fragmentransaction.add(R.id.container,EntertainmentFragment())

        fragmentransaction.hide(sportsFragment())
        fragmentransaction.hide(ItFragment())
        fragmentransaction.hide(EntertainmentFragment())
*/
        fragmentransaction.commit()

        btnavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    fragcall(HomeFragment(),true)
                    true
                }

                R.id.sports -> {
                    fragcall(sportsFragment(),false)
                    true
                }

                R.id.entertainment -> {
                    fragcall(EntertainmentFragment(),false)
                    true
                }

                R.id.Tech -> {
                    fragcall(ItFragment(),false)
                    true
                }

                else -> false
            }

        }




    }
    fun fragcall(frag:Fragment, flag:Boolean){/*
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // Hide all fragments
        listOf( sportsFragment, ItFragment, EntertainmentFragment).forEach {
            if (supportFragmentManager.fragments.contains(frag)) {
                fragmentTransaction.hide(frag)
            }
        }

        // Show the selected fragment
        if (supportFragmentManager.fragments.contains(frag)) {
            fragmentTransaction.show(frag)
        } else {
            fragmentTransaction.add(R.id.container, frag)
        }

        fragmentTransaction.commit()


*/


          val frmanager:FragmentManager = supportFragmentManager
        val frtrasaction : FragmentTransaction = frmanager.beginTransaction()



         if(flag) {
            frtrasaction.replace(R.id.container, frag)
            frtrasaction.commit()
        }
      else{
            frtrasaction.replace(R.id.container, frag)
            frtrasaction.commit()
      }
    }
}






