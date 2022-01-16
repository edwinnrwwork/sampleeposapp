package com.edwinnrw.posapp.presentation.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edwinnrw.posapp.R
import com.edwinnrw.posapp.databinding.ItemProductBinding
import com.edwinnrw.posapp.databinding.ItemSelectedProductBinding
import com.edwinnrw.posapp.domain.entity.ProductsEntity
import com.edwinnrw.posapp.util.Constant.MODIFIER_PRODUCT
import com.edwinnrw.posapp.util.Constant.PRICE
import com.edwinnrw.posapp.util.Constant.PRODUCT_NAME
import com.edwinnrw.posapp.util.Constant.QUANTITY
import com.edwinnrw.posapp.util.numberFormat


class ProductSelectedViewHolder(private val binding: ItemSelectedProductBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(product: HashMap<String,String>) {
        with(binding) {
            itemView.apply {
                val productName =  product[PRODUCT_NAME]?:""
                val quantity = product[QUANTITY]?:""
                tvProductName.text = "${productName} (${quantity}x)"
                val price = (product[PRICE]?:"0").toInt()
                tvProductPrice.text = "Rp${numberFormat(price.toString())}"
                tvProductOption.text = product[MODIFIER_PRODUCT]?:"-"
            }
        }

    }
}