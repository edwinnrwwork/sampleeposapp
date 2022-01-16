package com.edwinnrw.posapp.presentation.di.componen


import android.app.Application
import com.edwinnrw.posapp.BaseApplication
import com.edwinnrw.posapp.presentation.di.builder.ProductBuilder
import com.edwinnrw.posapp.presentation.di.module.NetworkModule
import com.edwinnrw.posapp.presentation.di.module.RepositoryModule
import com.edwinnrw.posapp.presentation.di.module.UseCaseModule
import com.edwinnrw.posapp.presentation.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


@AppScope
@Component(modules = [AndroidInjectionModule::class,
    RepositoryModule::class, UseCaseModule::class,
    ProductBuilder::class,NetworkModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: BaseApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}