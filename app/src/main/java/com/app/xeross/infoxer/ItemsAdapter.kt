package com.app.xeross.infoxer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.xeross.infoxer.model.ItemModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.items_cell.view.*

class ItemsAdapter(val context: Context,
                   val itemsList: List<ItemModel>) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemsViewHolder(LayoutInflater.from(context).inflate(R.layout.items_cell, parent, false))

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.textViewTitleItems.text = itemsList[position].titleItem
        holder.textViewStateItems.text = itemsList[position].stateItem
        holder.textViewNumberItems.text = itemsList[position].numberItem
        Glide.with(context).load(itemsList[position].imageItem).into(holder.imageViewImageItems)
    }

    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitleItems = itemView.itemsCell_TitleItems!!
        val imageViewImageItems = itemView.itemsCell_ImageItems!!
        val textViewNumberItems = itemView.itemsCell_NumberItems!!
        val textViewStateItems = itemView.itemsCell_StateItems!!
    }
}