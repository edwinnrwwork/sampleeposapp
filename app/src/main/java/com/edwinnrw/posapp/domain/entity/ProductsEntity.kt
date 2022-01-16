package com.edwinnrw.posapp.domain.entity

import com.google.gson.annotations.SerializedName

data class ProductsEntity(
    val image: String,
    val name: String,
    val price: String
)