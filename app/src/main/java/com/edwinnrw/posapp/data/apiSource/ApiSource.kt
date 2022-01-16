package com.edwinnrw.posapp.data.apiSource

import com.edwinnrw.posapp.data.entity.ProductsDataSourceApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiSource {

    @GET("/products.json")
    fun fetchProducts() : Single<List<ProductsDataSourceApi>>


}