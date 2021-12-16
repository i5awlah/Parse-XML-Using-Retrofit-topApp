package com.example.parsexmlusingretrofitmac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parsexmlusingretrofitmac.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"

    lateinit var binding: ActivityMainBinding
    lateinit var appRecyclerView: RecyclerView
    lateinit var appAdapter: AppAdapter

    var numberOFApp = 10

    var appTitles = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()

        binding.btnFetch.setOnClickListener {
            try {
                numberOFApp = binding.etNumber.text.toString().toInt()
                appTitles.clear()
                if (numberOFApp != 0)
                    fetchData()

            } catch (e: Exception) {
                Toast.makeText(this, "Enter number only", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun setupRV() {
        appRecyclerView = binding.rvApp
        appAdapter = AppAdapter(appTitles)
        appRecyclerView.adapter = appAdapter
        appRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchData() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call = apiInterface?.feed(numberOFApp)

        call!!.enqueue(object : Callback<RssFeed?> {
            override fun onResponse(call: Call<RssFeed?>, response: Response<RssFeed?>) {
                Log.d(TAG, "onResponse: feed: " + response.body().toString())
                Log.d(TAG, "onResponse: Server Response: $response")
                title = response.body()?.title

                val entries = response.body()!!.entries
                for (entry in entries!!) {
                    Log.d(TAG, "title: " + entry.title)
                    appTitles.add(entry.title!!)
                }
                appAdapter.update(appTitles)
            }

            override fun onFailure(call: Call<RssFeed?>, t: Throwable) {
                Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.message)
                Toast.makeText(this@MainActivity, "An Error Occured", Toast.LENGTH_SHORT).show()
            }
        })
    }
}