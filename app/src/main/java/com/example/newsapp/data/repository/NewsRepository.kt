package com.example.newsapp.data.repository

import com.example.newsapp.data.restAPI.ApiClient

/*
class NewsRepository(val db: ArticleDatabase) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        ApiClient.create().getBreakingNews(countryCode, pageNumber)

//    suspend fun searchNews()
}*/

class NewsRepository() {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        ApiClient.create().getBreakingNews(countryCode, pageNumber)
}
