

package com.edwinnrw.posapp.presentation.di.module

import com.edwinnrw.posapp.data.repository.ProductRepositoryContract
import com.edwinnrw.posapp.domain.usecase.ProductUseCase
import com.edwinnrw.posapp.domain.usecase.ProductUseCaseContract
import com.edwinnrw.posapp.presentation.di.scope.AppScope
import dagger.Module
import dagger.Provides


/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class UseCaseModule {


    @AppScope
    @Provides
    internal fun provideProductUseCase(productRepository: ProductRepositoryContract): ProductUseCaseContract {
        return ProductUseCase(productRepository)

    }

}