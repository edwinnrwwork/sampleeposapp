package com.edwinnrw.posapp.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edwinnrw.posapp.databinding.ItemProductBinding
import com.edwinnrw.posapp.databinding.ItemSelectedProductBinding
import com.edwinnrw.posapp.domain.entity.ProductsEntity


class ProductSelectedAdapter(private val products: MutableList<HashMap<String,String>>)
    : RecyclerView.Adapter<ProductSelectedViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSelectedViewHolder{
        val binding = ItemSelectedProductBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductSelectedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductSelectedViewHolder, position: Int) {
        holder.bindTo(products[position])

    }

}