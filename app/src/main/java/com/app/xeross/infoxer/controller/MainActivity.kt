package com.app.xeross.infoxer.controller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import android.util.TypedValue
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.app.xeross.infoxer.CategoriesAdapter
import com.app.xeross.infoxer.LoginActivity

import com.app.xeross.infoxer.R
import com.app.xeross.infoxer.model.CategoryModel
import com.app.xeross.infoxer.utils.*
import com.app.xeross.infoxer.viewmodel.CategoryViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.content_main.*

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CategoriesAdapter
    private lateinit var categoriesList: ArrayList<CategoryModel>
    private lateinit var imagesCategories: IntArray
    private lateinit var categoryTitleList: List<String>
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeCategories()
        configureUI()
        configureSwitchButton()

        if (FirebaseAuth.getInstance().currentUser == null) startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun configureUI() {
        activityMain_RecyclerView.layoutManager = GridLayoutManager(this, 2)
        activityMain_RecyclerView.addItemDecoration(GridSpacingItemDecoration(2, Utils.dpToPx(10, this), true))
        activityMain_RecyclerView.itemAnimator = DefaultItemAnimator()
        adapter = CategoriesAdapter(this, categoriesList)
        activityMain_RecyclerView.adapter = adapter

        activityMain_RecyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                var intent = Intent(this@MainActivity, CategoryActivity::class.java)
                intent.putExtra(TITLE_CATEGORY, categoryTitleList[position])
                intent.putExtra(IMAGE_CATEGORY, imagesCategories[position])
                startActivity(intent)
            }
        })
    }

    private fun configureSwitchButton() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, 0)
        activityMain_Switch.setOnCheckedChangeListener { _, isChecked ->
            val color = if (isChecked) R.color.backgroung_black else R.color.cardview_light_background
            activityMain_background.setBackgroundResource(color)

            val editor = sharedPreferences.edit()
            editor.putBoolean(SWITCH_STATE_DARK_MODE, isChecked)
            editor.apply()
        }
        activityMain_Switch.isChecked = sharedPreferences.getBoolean(SWITCH_STATE_DARK_MODE, false)
    }

    private fun initializeCategories() {
        imagesCategories = intArrayOf(R.drawable.music, R.drawable.serie, R.drawable.film, R.drawable.animerandom2, R.drawable.jeu, R.drawable.bouffe)
        categoryTitleList = getString(R.string.title_categories).split(',')
        categoriesList = ArrayList()
        val categoriesTitleSize = categoryTitleList.size - 1
        for (i in 0..categoriesTitleSize) {
            categoriesList.add(CategoryModel(categoryTitleList[i], 0, imagesCategories[i]))
        }
    }

    private fun configureViewModel() {
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    }
}
