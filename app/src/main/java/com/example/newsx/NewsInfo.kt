package com.example.newsx

data class NewsInfo(
    val status: String,
    val totalResults: Int,
    val articles: MutableList<Article>
)