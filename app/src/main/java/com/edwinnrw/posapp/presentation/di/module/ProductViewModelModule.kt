package com.edwinnrw.posapp.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edwinnrw.posapp.presentation.di.scope.ProductScope
import com.edwinnrw.posapp.presentation.viewModel.ProductViewModel
import com.edwinnrw.posapp.util.ViewModelFactory
import com.edwinnrw.posapp.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProductViewModelModule {

    @ProductScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    @ProductScope
    internal abstract fun bindViewModel(viewModel: ProductViewModel): ViewModel



}