package com.edwinnrw.posapp.data.repository

import com.edwinnrw.posapp.data.entity.ProductsDataSourceApi
import io.reactivex.rxjava3.core.Single

interface ProductRepositoryContract {

    fun fetchProduct() : Single<List<ProductsDataSourceApi>>
}