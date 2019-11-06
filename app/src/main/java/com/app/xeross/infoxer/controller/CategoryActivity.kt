package com.app.xeross.infoxer.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.xeross.infoxer.ItemsAdapter

import com.app.xeross.infoxer.R
import com.app.xeross.infoxer.model.ItemModel
import com.app.xeross.infoxer.utils.IMAGE_CATEGORY
import com.app.xeross.infoxer.utils.TITLE_CATEGORY
import com.app.xeross.infoxer.viewmodel.CategoryViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_category.*

import java.util.ArrayList

class CategoryActivity : AppCompatActivity() {

    private lateinit var adapter: ItemsAdapter
    private lateinit var itemsList: ArrayList<ItemModel>
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        configureViewModel()
        testInitializeItems()
        configureUI()
    }

    private fun configureUI() {
        if (intent == null) finish()
        Glide.with(this).load(intent.getIntExtra(IMAGE_CATEGORY, R.drawable.animeflou)).into(activityCategory_ImageItem)
        activityCategory_TitleItem.text = intent.getStringExtra(TITLE_CATEGORY)
        activityCategory_RecyclerViewItem.layoutManager = LinearLayoutManager(this)
        adapter = ItemsAdapter(this, itemsList)
        activityCategory_RecyclerViewItem.adapter = adapter
        activityCategory_ButtonBack.setOnClickListener { finish() }
    }

    private fun testInitializeItems() {
        itemsList = ArrayList()
        val covers = intArrayOf(R.drawable.hunterxhunter, R.drawable.myheroacademia, R.drawable.onepiece, R.drawable.naruto, R.drawable.attackontitan, R.drawable.fairytails, R.drawable.deathnote, R.drawable.onepunchman)
        var a = ItemModel("1", covers[0], "Hunter x Hunter", "Terminé")
        itemsList.add(a)

        a = ItemModel("2", covers[1], "(non) My Hero academia", "En attente")
        itemsList!!.add(a)

        a = ItemModel("3", covers[2], "One piece", "En attente")
        itemsList!!.add(a)

        a = ItemModel("4", covers[3], "Naturo", "En cours")
        itemsList!!.add(a)

        a = ItemModel("5", covers[4], "L’attaque des titans", "En attente")
        itemsList!!.add(a)

        a = ItemModel("6", covers[5], "Fairy tails", "En cours")
        itemsList!!.add(a)

        a = ItemModel("7", covers[6], "Death Note", "En cours")
        itemsList!!.add(a)

        a = ItemModel("8", covers[7], "One punch man", "En cours")
        itemsList!!.add(a)
    }

    private fun configureViewModel() {
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    }

}
