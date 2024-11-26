package com.example.newsapp.data.repository

import com.example.newsapp.data.restAPI.ApiClient
import com.example.newsapp.roomDB.ArticleDatabase

class NewsRepository(val db: ArticleDatabase) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        ApiClient.create().getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        ApiClient.create().searchForNews(searchQuery, pageNumber)
}
