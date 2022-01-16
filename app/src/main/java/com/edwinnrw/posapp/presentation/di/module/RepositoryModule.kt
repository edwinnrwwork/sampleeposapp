

package com.edwinnrw.posapp.presentation.di.module

import android.app.Application
import com.edwinnrw.posapp.data.apiSource.ApiSource
import com.edwinnrw.posapp.data.repository.ProductRepository
import com.edwinnrw.posapp.data.repository.ProductRepositoryContract
import com.edwinnrw.posapp.presentation.di.scope.AppScope

import dagger.Module
import dagger.Provides


/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class RepositoryModule {


    @AppScope
    @Provides
    internal fun provideProductRepository(apiSource: ApiSource):
            ProductRepositoryContract {
        return ProductRepository(apiSource)

    }


}