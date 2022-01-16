package com.edwinnrw.posapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edwinnrw.posapp.domain.entity.ProductsEntity
import com.edwinnrw.posapp.domain.usecase.ProductUseCaseContract
import com.edwinnrw.posapp.presentation.state.ProductsState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class ProductViewModel @Inject constructor(private val productUseCase: ProductUseCaseContract) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _productState: MutableLiveData<ProductsState> = MutableLiveData()

    val productState: LiveData<ProductsState>
        get() = _productState

    fun fetchProducts(){
        val disposable = productUseCase.fetchProduct()
            .doOnSubscribe {
                _productState.value = ProductsState.Loading

            }
            .doAfterTerminate {
                _productState.value = ProductsState.HideLoading
            }
            .subscribe(
                {

                    _productState.value = ProductsState.Success(it)
                },
                {
                    _productState.value = ProductsState.Error(it.message?:"")

                }
            )

        compositeDisposable.addAll(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}