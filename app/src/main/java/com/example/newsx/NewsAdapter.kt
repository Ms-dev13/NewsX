package com.example.newsx

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class NewsAdapter (private var context: Context,private var newsList: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    fun updateNewsList(newList: List<Article>) {
        newsList = newList
        notifyDataSetChanged() // Refresh the RecyclerView
    }

    // ViewHolder class to hold and bind views
   inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.articleTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.articleDescription)
        val imageView: ImageView = itemView.findViewById(R.id.articleImage)
        val source:TextView = itemView.findViewById(R.id.articleSource)
        val time:TextView = itemView.findViewById(R.id.articleDateTime)
        val const:ConstraintLayout = itemView.findViewById(R.id.mainconstraint)
    }

    // Inflate the item layout and create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsArticle = newsList[position]
        holder.titleTextView.text = newsArticle.title
        holder.descriptionTextView.text = newsArticle.description
        holder.source.text = newsArticle.source.name
        holder.time.text = newsArticle.publishedAt


        // Load the image using a library like Glide or Picasso
        Glide.with(holder.itemView.context)
            .load(newsArticle.urlToImage)
            .into(holder.imageView)

        holder.const.setOnClickListener(){

            val intent = Intent(context,News_clicked::class.java)
            intent.putExtra("title",newsArticle.title)
            intent.putExtra("imageUrl",newsArticle.urlToImage)
            intent.putExtra("description",newsArticle.description)
            context.startActivity(intent)

        }

    }

    // Return the total item count
    override fun getItemCount(): Int {
        return newsList.size
    }
}