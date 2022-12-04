package com.gten.practicaltest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.gten.practicaltest.R
import com.gten.practicaltest.adapter.ProductAdapter
import com.gten.practicaltest.databinding.ActivityMainBinding
import com.gten.practicaltest.iinterface.GeneralClickListener
import com.gten.practicaltest.model.Products
import com.gten.practicaltest.sealed.MainEvent
import com.gten.practicaltest.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.setObserver()

        lifecycleScope.launchWhenStarted {
            viewModel.event.collect{
                setProductList(it)
            }
        }

        productAdapter = ProductAdapter(object: GeneralClickListener{
            override fun onProductClick(product: Products) {
                val intent = Intent(this@MainActivity, ProductDetailActivity::class.java)
                intent.putExtra("product", Gson().toJson(product))
                startActivity(intent)
            }
        })
        binding.rcvProducts.apply {
            adapter = productAdapter
        }
    }

    private fun setProductList(it: MainEvent) {
        when(it)
        {
            MainEvent.ShowProgressBar-> binding.progressbar.visibility = View.VISIBLE
            is MainEvent.SetProductList->{
                if (it.list.size > 0)
                    productAdapter.productList.clear()
                    productAdapter.productList.addAll(it.list)
                    productAdapter.notifyDataSetChanged()
                binding.progressbar.visibility = View.GONE
            }
            is MainEvent.ShowToast->{
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                binding.progressbar.visibility = View.GONE
            }
            else -> {

            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProductList()
    }
}