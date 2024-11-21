package com.example.newsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.viewModels.NewsViewModels

class NewsViewModelProvider(private val newsRepository: NewsRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModels(newsRepository) as T
    }
}