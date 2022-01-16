package com.edwinnrw.posapp.presentation.state

import com.edwinnrw.posapp.domain.entity.ProductsEntity

sealed  class ProductsState(
    val data:List<ProductsEntity> = mutableListOf(),
    val message:String? = null,
    val code: Int?=0
) {
    class Success(data: List<ProductsEntity>) : ProductsState(data)

    object Loading : ProductsState()

    object HideLoading  : ProductsState()

    class Error(message: String) : ProductsState(message = message)



}
