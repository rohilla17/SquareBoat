package com.example.newsapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.newsapp.DaggerViewModelInjector
import com.example.newsapp.network.NetworkModule

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector.builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ArticleListViewModel -> injector.inject(this)
        }
    }

}