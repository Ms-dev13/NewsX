package com.example.newsx

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EntertainmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EntertainmentFragment : Fragment() {
    // TODO: Rename and change types of parameters
 private lateinit var  recyler:RecyclerView


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entertainment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyler = view.findViewById(R.id.entertainment_recycler_view)
        recyler.adapter = context?.let { NewsAdapter(it,emptyList()) }
        recyler.layoutManager = LinearLayoutManager(context)

        getEntertainmentNews()
    }

    fun getEntertainmentNews(){

        val api = Retrofit.Builder().
                baseUrl("https://newsapi.org/v2/").
                addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApInterface::class.java)

        api.getNews("us","entertainment","69de4efa7ada4f4da1bb5d4c42701c71")
            .enqueue(object : retrofit2.Callback<NewsInfo> {
                override fun onResponse(call: Call<NewsInfo>, response: Response<NewsInfo>) {

                    if(response.isSuccessful){
                        response.body()?.let {
                            /*  for (comm in it.articles){
                              newsAdapter.updateNewsList()
                              // Log.i("News", comm.title)
                              }*/
                            recyler.adapter = context?.let { it1 -> NewsAdapter(it1,it.articles) }
                        }
                    }

                }

                override fun onFailure(call: Call<NewsInfo>, t: Throwable) {
                    Log.i("News","${t.message}")
                }

            })}


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EntertainmentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EntertainmentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}