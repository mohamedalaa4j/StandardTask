package com.example.standardtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.standardtask.R
import com.example.standardtask.data.models.received.HomePageComponantsModel
import com.example.standardtask.databinding.RvItemMostOrderedItemsBinding
import com.example.standardtask.utilities.Utilities

class RvAdapterMostOrderedItems(
    private val items: List<HomePageComponantsModel.MostSellItems.Data?>, private val listenerId: (id: String) -> Unit,
) : RecyclerView.Adapter<RvAdapterMostOrderedItems.ViewHolder>() {


    ///// ViewHolder class using ViewBinding instead of View
    inner class ViewHolder(binding: RvItemMostOrderedItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        val ivCover = binding.ivCover
        val tvName = binding.tvName
        val tvDescription = binding.tvDescription

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemMostOrderedItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        val context = holder.itemView.context

            val urlCover = "https://satatechnologygroup.net:3301/${item?.menuCategoriesItems?.photo}"
            Glide.with(context).load(urlCover).error(R.drawable.no_image).into(holder.ivCover)


        if (Utilities.deviceLanguage() == "ar"){
            holder.tvDescription.text = item?.menuCategoriesItems?.descriptions
            holder.tvName.text = item?.menuCategoriesItems?.name
        }else if (Utilities.deviceLanguage() == "en"){
            holder.tvDescription.text = item?.menuCategoriesItems?.descriptionsEn
            holder.tvName.text = item?.name
        }




        holder.itemView.setOnClickListener {
            listenerId(item?.name.toString())
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }
}