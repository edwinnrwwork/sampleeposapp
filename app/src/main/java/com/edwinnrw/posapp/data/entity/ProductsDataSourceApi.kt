package com.edwinnrw.posapp.data.entity

import com.google.gson.annotations.SerializedName

data class ProductsDataSourceApi(
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?
)