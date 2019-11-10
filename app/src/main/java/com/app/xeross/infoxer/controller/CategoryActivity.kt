package com.app.xeross.infoxer.controller

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.xeross.infoxer.ItemsAdapter

import com.app.xeross.infoxer.R
import com.app.xeross.infoxer.ViewModelFactory
import com.app.xeross.infoxer.model.ItemModel
import com.app.xeross.infoxer.utils.IMAGE_CATEGORY
import com.app.xeross.infoxer.utils.PICK_IMAGE_REQUEST
import com.app.xeross.infoxer.utils.TITLE_CATEGORY
import com.app.xeross.infoxer.utils.Utils
import com.app.xeross.infoxer.viewmodel.ItemViewModel
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.popup_add_item.view.*
import java.io.IOException

class CategoryActivity : AppCompatActivity() {

    private lateinit var adapter: ItemsAdapter
    private lateinit var itemsList: ArrayList<ItemModel>
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var viewDialog: View
    private lateinit var bitmap: Bitmap
    private lateinit var itemModel: ItemModel
    private var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        configureViewModel()
        initializeItems()
        configureUI()

        activityCategory_ButtonAddItem.setOnClickListener {
            viewDialog = LayoutInflater.from(this).inflate(R.layout.popup_add_item, null)
            val builder = AlertDialog.Builder(this).setView(viewDialog)
            val alertDialog = builder.show()
            viewDialog.popupAddItem_ButtonImage.setOnClickListener {
                launchGallery()
            }
            viewDialog.popupAddItem_Cancel.setOnClickListener {
                alertDialog.dismiss()
            }
            viewDialog.popupAddItem_Add.setOnClickListener {
                itemModel = ItemModel("0",
                        Utils.encodeBitmap(bitmap),
                        viewDialog.popupAddItem_Name.text.toString(),
                        viewDialog.popupAddItem_State.text.toString())
                itemViewModel.createItemToFirebase(itemModel,
                        intent.getStringExtra(TITLE_CATEGORY),
                        FirebaseAuth.getInstance().currentUser!!.uid)
                alertDialog.dismiss()

            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) return
            filePath = data.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                viewDialog.popupAddItem_ButtonImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun configureUI() {
        if (intent == null) finish()
        Glide.with(this).load(intent.getIntExtra(IMAGE_CATEGORY, 0)).into(activityCategory_ImageItem)
        activityCategory_TitleItem.text = intent.getStringExtra(TITLE_CATEGORY)
        activityCategory_RecyclerViewItem.layoutManager = LinearLayoutManager(this)
        adapter = ItemsAdapter(this, itemsList)
        activityCategory_RecyclerViewItem.adapter = adapter
        activityCategory_ButtonBack.setOnClickListener { finish() }
    }

    private fun initializeItems() {
        itemsList = ArrayList()
        itemViewModel.getItemsToFirebase(intent.getStringExtra(TITLE_CATEGORY),
                FirebaseAuth.getInstance().currentUser!!.uid).observe(this, Observer { items ->
            itemsList.addAll(items)
            adapter.notifyDataSetChanged()
        })
    }

    private fun configureViewModel() {
        itemViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(ItemViewModel::class.java)
    }

}
