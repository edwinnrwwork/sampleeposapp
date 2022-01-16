package com.edwinnrw.posapp.domain.usecase

import com.edwinnrw.posapp.domain.entity.ProductsEntity
import io.reactivex.rxjava3.core.Single

interface ProductUseCaseContract {
    fun fetchProduct() : Single<List<ProductsEntity>>

}