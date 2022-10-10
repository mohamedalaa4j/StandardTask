package com.example.standardtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.standardtask.R
import com.example.standardtask.data.models.received.HomePageComponantsModel
import com.example.standardtask.databinding.RvItemBestSellingRestaurantsBinding

class RvAdapterBestSellingRestaurants(
    private val items: List<HomePageComponantsModel.GetMostOrderedBranch.Data?>, private val listenerId: (id: String) -> Unit,
) : RecyclerView.Adapter<RvAdapterBestSellingRestaurants.ViewHolder>() {


    ///// ViewHolder class using ViewBinding instead of View
    inner class ViewHolder(binding: RvItemBestSellingRestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val ivCover = binding.ivCover
        val ivLogo = binding.ivLogo
        val tvName = binding.tvName
        val tvIsOpen = binding.tvIsOpen

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemBestSellingRestaurantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        val context = holder.itemView.context


        holder.tvName.text = item?.name

        val urlCover = "https://satatechnologygroup.net:3301/${item?.cover}"
        Glide.with(context).load(urlCover).error(R.drawable.no_image).into(holder.ivCover)

        val urlLogo = "https://satatechnologygroup.net:3301/${item?.logo}"
        Glide.with(context).load(urlLogo).error(R.drawable.no_image).into(holder.ivLogo)


        if (item?.isOpen == "true"){
            holder.tvIsOpen.text = context.getString(R.string.open)
            holder.tvIsOpen.setTextColor(ContextCompat.getColor(context, R.color.green))
        }else if (item?.isOpen == "false"){
            holder.tvIsOpen.text = context.getString(R.string.closed)
            holder.tvIsOpen.setTextColor(ContextCompat.getColor(context, R.color.red))
        }



        holder.itemView.setOnClickListener {
            listenerId(item?.name.toString())
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }
}