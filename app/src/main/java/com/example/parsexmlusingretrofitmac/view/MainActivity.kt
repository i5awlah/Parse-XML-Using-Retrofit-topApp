package com.example.parsexmlusingretrofitmac.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parsexmlusingretrofitmac.AppFeed
import com.example.parsexmlusingretrofitmac.databinding.ActivityMainBinding
import com.example.parsexmlusingretrofitmac.model.App
import com.example.parsexmlusingretrofitmac.recyclerview.AppAdapter
import com.example.parsexmlusingretrofitmac.services.APIClient
import com.example.parsexmlusingretrofitmac.services.APIInterface
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

    var appTitles = arrayListOf<App>()

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
        appAdapter = AppAdapter(appTitles, this)
        appRecyclerView.adapter = appAdapter
        appRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchData() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call = apiInterface?.feed(numberOFApp)

        call!!.enqueue(object : Callback<AppFeed?> {
            override fun onResponse(call: Call<AppFeed?>, response: Response<AppFeed?>) {
                Log.d(TAG, "onResponse: feed: " + response.body().toString())
                Log.d(TAG, "onResponse: Server Response: $response")

                val entries = response.body()!!.entries
                for (entry in entries!!) {
                    Log.d(TAG, "title: " + entry.title)
                    Log.d(TAG, "image: " + entry.imageUrl!![0].url)
                    val title = entry.title!!
                    val name = entry.name!!
                    val image = entry.imageUrl!![0].url!!
                    val summary = entry.summary!!

                    appTitles.add( App(title,name,summary,image) )
                }
                appAdapter.update(appTitles)
            }

            override fun onFailure(call: Call<AppFeed?>, t: Throwable) {
                Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.message)
                Toast.makeText(this@MainActivity, "An Error Occured", Toast.LENGTH_SHORT).show()
            }
        })
    }
}