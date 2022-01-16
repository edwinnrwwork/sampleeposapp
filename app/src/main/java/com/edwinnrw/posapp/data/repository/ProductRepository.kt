package com.edwinnrw.posapp.data.repository

import com.edwinnrw.posapp.data.apiSource.ApiSource

class ProductRepository(private val apiSource: ApiSource) : ProductRepositoryContract{

    override fun fetchProduct() = apiSource.fetchProducts()
}