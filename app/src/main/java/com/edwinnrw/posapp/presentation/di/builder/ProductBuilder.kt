package com.edwinnrw.posapp.presentation.di.builder


import com.edwinnrw.posapp.presentation.ui.MainActivity
import com.edwinnrw.posapp.presentation.di.scope.ProductScope
import com.edwinnrw.posapp.presentation.di.module.ProductViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProductBuilder {

    @ProductScope
    @ContributesAndroidInjector(modules = [(ProductViewModelModule::class)])
    internal abstract fun bindMainActivity(): MainActivity



}
