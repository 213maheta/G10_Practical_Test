package com.gten.practicaltest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gten.practicaltest.databinding.ItemProductBinding
import com.gten.practicaltest.iinterface.GeneralClickListener
import com.gten.practicaltest.model.Products

class ProductAdapter(val listener:GeneralClickListener): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    val productList = mutableListOf<Products>()

    class ViewHolder(val binding: ItemProductBinding):RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val products = productList.get(position)
        holder.binding.product = products
        Glide.with(holder.binding.image).load(products.thumbnail).into(holder.binding.image)
        holder.binding.root.setOnClickListener {
            listener.onProductClick(products)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
