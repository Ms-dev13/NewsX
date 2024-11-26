package com.example.newsx

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyler1:RecyclerView

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
        return inflater.inflate(R.layout.fragment_it, container, false)

    }
/*So, think of Context as the "house address" that gives you access to everything
 you might need in that house—the items, the services, and the overall environment!
 */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyler1 = view.findViewById(R.id.it_Recycler_view)
        recyler1.adapter = context?.let { NewsAdapter(it,emptyList()) }
        recyler1.layoutManager = LinearLayoutManager(context)
        getitnews()


    }

    private fun getitnews(){

        val api = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApInterface::class.java)

        api.getNews("us","technology","69de4efa7ada4f4da1bb5d4c42701c71").
        enqueue(object:Callback<NewsInfo>  {
            override fun onResponse(call: Call<NewsInfo>, response: Response<NewsInfo>) {

                if(response.isSuccessful){
                    response.body()?.let {
                        /*  for (comm in it.articles){
                          newsAdapter.updateNewsList()
                          // Log.i("News", comm.title)
                          }*/
                        recyler1.adapter = context?.let { it1 -> NewsAdapter(it1,it.articles) }
                    }
                }


            }

            override fun onFailure(call: Call<NewsInfo>, t: Throwable) {
                Log.i("News","${t.message}")
            }

        } )

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}