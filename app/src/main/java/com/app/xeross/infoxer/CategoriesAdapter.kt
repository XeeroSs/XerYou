package com.app.xeross.infoxer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.app.xeross.infoxer.model.CategoryModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_cell.view.*

class CategoriesAdapter(val context: Context,
                        val categoriesList: List<CategoryModel>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CategoriesViewHolder(LayoutInflater.from(context).inflate(R.layout.category_cell, parent, false))

    override fun getItemCount() = categoriesList.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        updateCategories(position, holder)
    }

    private fun updateCategories(position: Int, holder: CategoriesViewHolder) {
        Glide.with(context).load(categoriesList[position].categoryImage).into(holder.imageButtonCategoryImage)
        holder.textViewCategoryTitle.text = categoriesList[position].categoryTitle
        holder.textViewCategoryCount.text = context.getString(R.string.category_count, categoriesList[position].categoryCount)
        holder.imageViewCategoryMenu.setOnClickListener {
            showPopupMenu(holder.imageViewCategoryMenu)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.menuInflater.inflate(R.menu.menu_categorie, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_nothing -> {
                    Toast.makeText(context, context.getString(R.string.not_available), Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
        popupMenu.show()
    }

    class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCategoryTitle = itemView.categoryCell_TextView_CategoryTitle!!
        val imageViewCategoryMenu = itemView.categoryCell_ImageView_CategoryMenu!!
        val textViewCategoryCount = itemView.categoryCell_TextView_CategoryCount!!
        val imageButtonCategoryImage = itemView.categoryCell_ImageButton_CategoryImage!!
    }
}