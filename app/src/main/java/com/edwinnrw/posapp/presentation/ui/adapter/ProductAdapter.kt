package com.edwinnrw.posapp.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edwinnrw.posapp.databinding.ItemProductBinding
import com.edwinnrw.posapp.domain.entity.ProductsEntity


class ProductAdapter(private val products: MutableList<ProductsEntity>,private val onClick:(product:ProductsEntity)->Unit)
    : RecyclerView.Adapter<ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder{
        val binding = ItemProductBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindTo(products[position])
        holder.itemView.setOnClickListener {
            onClick(products[position])
        }
    }

}