package com.example.newsx

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ActionMode.Callback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

private fun <T> Call<T>.enqueue(any: Any, function: () -> Unit) {

}

class HomeFragment() : Fragment() {

    // Retrofit API Interface
    val api = RetrofitClient.instance.create(ApInterface::class.java)

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    lateinit var arr:ArrayList<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val api = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApInterface::class.java)

        api.getNews("us","general","69de4efa7ada4f4da1bb5d4c42701c71")
            .enqueue(object : retrofit2.Callback<NewsInfo> {
                override fun onResponse(call: Call<NewsInfo>, response: Response<NewsInfo>) {

                    if(response.isSuccessful){
                        response.body()?.let {
                            /*  for (comm in it.articles){
                              newsAdapter.updateNewsList()
                              // Log.i("News", comm.title)
                              }*/
                          recyclerView.adapter = getContext()?.let { it1 -> NewsAdapter(it1,it.articles) }
                        }
                    }

                }

                override fun onFailure(call: Call<NewsInfo>, t: Throwable) {
                    Log.i("News","${t.message}")
                }

            })




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i("HomeFragment", " I am here in OnCreateView")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView after the view is created
        recyclerView = view.findViewById(R.id.home_recycler_view)

        recyclerView.adapter = getContext()?.let { NewsAdapter(it,emptyList()) }

        recyclerView.layoutManager = LinearLayoutManager(context)

        Log.i("HomeFragment", " I am here in onViewCreated")
        // Fetch the news data
        fetchNewsData()



    }
// package:com.example.newsx
    private fun fetchNewsData() {


        val api = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApInterface::class.java)

        api.getNews("us","general","69de4efa7ada4f4da1bb5d4c42701c71")
            .enqueue(object : retrofit2.Callback<NewsInfo> {
                override fun onResponse(call: Call<NewsInfo>, response: Response<NewsInfo>) {

                    if(response.isSuccessful){
                        response.body()?.let {
                          /*  for (comm in it.articles){
                            newsAdapter.updateNewsList()
                            // Log.i("News", comm.title)
                            }*/
                            recyclerView.adapter = getContext()?.let { it1 -> NewsAdapter(it1,it.articles) }
                        }
                    }

                }

                override fun onFailure(call: Call<NewsInfo>, t: Throwable) {
                    Log.i("News","${t.message}")
                }

            })



    /*
        val call = ApiClient.apiservice.getNews("us","technology","69de4efa7ada4f4da1bb5d4c42701c71")

        call.enqueue(object : Callback<NewsInfo>){
            override fun onResponse(call: Call<NewsInfo>, response: Response<NewsInfo>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    // Handle the retrieved post data
                } else {
                    // Handle error
                }
            }*/
}




    /*   // Use CoroutineScope for network call
       CoroutineScope(Dispatchers.IO).launch {
       Log.i("HomeFragment", " I am in coroutine scope: ")
           try {
        Log.i("HomeFragment", " Before request make ")
               val response = api.getNews("us", "technology", "69de4efa7ada4f4da1bb5d4c42701c71")

            Log.i("HomeFragment", " After request make ")

               if (response.isExecuted) {

                   val newsData = response.enqueue(NewsInfo)
             Log.i("HomeFragment", newsData.toString())
                   // Update RecyclerView with fetched data
                   withContext(Dispatchers.Main) {
                       newsData?.let {
                           newsAdapter.updateNewsList(it.articles)
                       }
                   }
               } else {
                   // Handle API errors
                   Log.i("HomeFragment", "Failed to Get data")
                   println("Error: ${response.code()}")
               }
           } catch (e: Exception) {
               e.printStackTrace()
           }
       }*/
    }

