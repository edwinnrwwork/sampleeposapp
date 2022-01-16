package com.edwinnrw.posapp.domain.mapper

import com.edwinnrw.posapp.data.entity.ProductsDataSourceApi
import com.edwinnrw.posapp.domain.entity.ProductsEntity


fun List<ProductsDataSourceApi>.map() : List<ProductsEntity>{
    return this.map { mapItemToEntity(it) }
}

private fun mapItemToEntity(item:ProductsDataSourceApi): ProductsEntity =
    ProductsEntity(
        name = item.name?:"",
        image = item.image?:"",
        price = item.price?:"",

    )