package com.watasolutions.w3_databinding_wm

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecycleAdapter: RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {


    public var titles:ArrayList<String> = ArrayList()
    private var itemSize = Int
    private val details = arrayOf("abc", "abc", "abc")



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }



    override fun onBindViewHolder(holder: RecycleAdapter.ViewHolder, position: Int) {
        val activity: RestaurantActivity? = holder.itemView.context as RestaurantActivity
        val map = activity?.getData()
        val data_names = map?.get("data_name")
        val data_addresses = map?.get("data_address")
        val data_images = map?.get("data_image")
        holder.itemTitle.text = data_names?.get(position)
        holder.itemDetail.text = data_addresses?.get(position)
        Glide.with(holder.itemView.context)
            .load(data_images?.get(position))
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return 20

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)

            itemView.setOnClickListener {
                val position:Int = absoluteAdapterPosition

                Toast.makeText(itemView.context, "This is item ${titles[position]} details", Toast.LENGTH_LONG).show()
            }
        }
    }

}

