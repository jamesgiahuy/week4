package com.watasolutions.w3_databinding_wm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

class RestaurantActivity : AppCompatActivity() {

    private var  layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecycleAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        layoutManager = LinearLayoutManager(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = layoutManager

        adapter = RecycleAdapter()
        recyclerView.adapter = adapter
    }
    fun getData(): HashMap<String, ArrayList<String>> {
        val jsondata = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "db_restaurant",
                "raw",
                applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }
        val map = HashMap<String, ArrayList<String>>()
        val outputJsonString = JSONObject(jsondata)
        val res_list = outputJsonString.getJSONArray("res_list") as JSONArray
        var names: ArrayList<String> = ArrayList()
        var images: ArrayList<String> = ArrayList()
        var addresses: ArrayList<String> = ArrayList()
        for (i in 0 until res_list.length()) {
            var name = res_list.getJSONObject(i).getString("Name")
            var image = res_list.getJSONObject(i).getString("MobilePicturePath")
            var address = res_list.getJSONObject(i).getString("Address")
            names.add(name)
            images.add(image)
            addresses.add(address)
        }
        map["data_name"] = names
        map["data_address"] = addresses
        map["data_image"] = images
//        Log.d("hienlog", res_list.toString())
        return map
    }
}