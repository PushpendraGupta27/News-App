package com.example.newsapp.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int = 0
){
    class Success<T>(data:T): Resource<T>(data)
    class Loading<T>(data:T?=null): Resource<T>(data)
    class Error<T>(message: String, code: Int=0, data: T?=null): Resource<T>(data, message, code)
    class InternetError<T>() : Resource<T>()
}