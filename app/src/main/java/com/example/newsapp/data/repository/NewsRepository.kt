package com.example.newsapp.data.repository

import com.example.newsapp.data.restAPI.ApiClient
import com.example.newsapp.model.response.Article
import com.example.newsapp.roomDB.ArticleDatabase

class NewsRepository(private val db: ArticleDatabase) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        ApiClient.create().getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        ApiClient.create().searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSaveNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticles(article)
}
