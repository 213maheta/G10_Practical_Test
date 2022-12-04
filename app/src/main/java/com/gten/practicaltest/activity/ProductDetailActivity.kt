package com.gten.practicaltest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.gten.practicaltest.R
import com.gten.practicaltest.adapter.ImageAdapter
import com.gten.practicaltest.databinding.ActivityMainBinding
import com.gten.practicaltest.databinding.ActivityProductDetailBinding
import com.gten.practicaltest.model.Products

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        val product = Gson().fromJson(intent.getStringExtra("product"), Products::class.java)

        val adapter = ImageAdapter()
        adapter.imagelist.addAll(product.images)
        binding.viewPager.adapter = adapter

        binding.product = product

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
        }.attach()

        binding.btnBookNow.setOnClickListener {
            Toast.makeText(this, "Thanks for your order", Toast.LENGTH_LONG).show()
        }

    }
}