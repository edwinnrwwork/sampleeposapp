package com.edwinnrw.posapp.presentation.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edwinnrw.posapp.R
import com.edwinnrw.posapp.databinding.ItemProductBinding
import com.edwinnrw.posapp.domain.entity.ProductsEntity
import com.edwinnrw.posapp.util.numberFormat


class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(product: ProductsEntity) {
        with(binding) {
            itemView.apply {
                tvProductName.text = product.name
                tvProductPrice.text = "Rp${numberFormat(product.price)}"
                Glide.with(this).load(product.image)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(ivImageProduct)
            }
        }

    }
}