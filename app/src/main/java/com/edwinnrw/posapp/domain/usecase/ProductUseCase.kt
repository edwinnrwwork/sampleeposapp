package com.edwinnrw.posapp.domain.usecase

import com.edwinnrw.posapp.data.repository.ProductRepositoryContract
import com.edwinnrw.posapp.domain.entity.ProductsEntity
import com.edwinnrw.posapp.domain.mapper.map
import com.edwinnrw.posapp.util.singleIo
import io.reactivex.rxjava3.core.Single

class ProductUseCase(private val productRepository: ProductRepositoryContract) : ProductUseCaseContract {
    override fun fetchProduct(): Single<List<ProductsEntity>> {
        return productRepository.fetchProduct()
            .map {
                it.map()
            }
            .compose(singleIo())
    }
}