package com.example.standardtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.standardtask.data.models.received.HomePageComponantsModel
import com.example.standardtask.databinding.RvItemBestSellingRestaurantsBinding

class RvAdapterBestSellingRestaurants(
    private val items: List<HomePageComponantsModel.GetMostOrderedBranch.Data?>, private val listenerId: (id: String) -> Unit,
) : RecyclerView.Adapter<RvAdapterBestSellingRestaurants.ViewHolder>() {


    ///// ViewHolder class using ViewBinding instead of View
    inner class ViewHolder(binding: RvItemBestSellingRestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        val imageView = binding.imageView
        val textView = binding.textView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemBestSellingRestaurantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        val context = holder.itemView.context


        holder.textView.text = item?.name

        val url = "https://satatechnologygroup.net:3301/${item?.cover}"
        Glide.with(context).load(url).into(holder.imageView)

        holder.itemView.setOnClickListener {
            listenerId(item?.name.toString())
        }


    }


    override fun getItemCount(): Int {
        return items.size
    }
}