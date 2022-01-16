

package com.edwinnrw.posapp.presentation.di.module

import android.app.Application
import com.edwinnrw.posapp.BuildConfig
import com.edwinnrw.posapp.data.apiSource.ApiSource
import com.edwinnrw.posapp.data.repository.ProductRepository
import com.edwinnrw.posapp.data.repository.ProductRepositoryContract
import com.edwinnrw.posapp.presentation.di.scope.AppScope

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class NetworkModule {


    @AppScope
    @Provides
    internal fun provideOkhttp():
            OkHttpClient {
        val okhttp = OkHttpClient.Builder()
        return okhttp.build()

    }

    @AppScope
    @Provides
    internal fun provideRetrofitClient(okHttpClient:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @AppScope
    @Provides
    internal fun provideApiSource(retrofit: Retrofit) : ApiSource{
        return retrofit.create(ApiSource::class.java)
    }

}