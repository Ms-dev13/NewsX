package com.example.newsx

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import java.security.AccessController.getContext
import kotlin.collections.ArrayList

// RecyclerView.Adapter is a generic class. We need to specify the type of ViewHolder it will use.
// This adapter will use a custom ViewHolder called myviewHolder that will be defined inside the CustomeAdapter class.

class CustomeAdapter(context:Context,private val dataSet: ArrayList<Datainfo>,private val selectedItems: MutableSet<Int>,
                     private val onItemSelected: (Boolean) -> Unit
) : RecyclerView.Adapter<CustomeAdapter.myviewHolder>() {



  // Whenever the user scrolls through the list, onCreateViewHolder() is called to create
        // a new ViewHolder object if one is not already available for reuse.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {
//It is responsible for inflating the layout for a single row/item and returning a ViewHolder that holds the inflated view.

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)
        return myviewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {
        val datainside = dataSet[position]

    //      holder.maincard.setCardBackgroundColor(datainside.background_color)
        holder.maintext.text = datainside.text
        holder.img.setImageResource(datainside.image)

      /*  if (selectedItems.contains(datainside.id)) {
            holder.maincard.setCardBackgroundColor(Color.LTGRAY)  // Selected color
        } else {
            holder.maincard.setCardBackgroundColor(Color.WHITE)   // Default color
        }
*/
        // Handle item click to toggle selection

        holder.maincard.setOnClickListener {
            if (selectedItems.contains(datainside.id)) {
                selectedItems.remove(datainside.id)  // Deselect item
                holder.mainconst.setBackgroundColor(Color.WHITE)
            } else {
                selectedItems.add(datainside.id)  // Select item
                holder.mainconst.setBackgroundColor( ContextCompat.getColor(holder.itemView.context, R.color.Selected_color))
            }

           onItemSelected(selectedItems.isNotEmpty())
        }



    }

    inner class myviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val maincard: MaterialCardView
        val mainconst:ConstraintLayout
        val maintext:TextView
        val img:ImageView

        init {

            maincard = itemView.findViewById(R.id.card)
            mainconst = itemView.findViewById(R.id.contraint)
            maintext = itemView.findViewById(R.id.textView2)
            img = itemView.findViewById(R.id.imageView3)

        }


    }

    fun getSelectedItems(): List<Datainfo> {
        return selectedItems.map { dataSet[it] }
    }



}