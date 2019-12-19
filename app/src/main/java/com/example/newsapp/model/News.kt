package com.example.newsapp.model

import com.example.newsapp.model.Article

data class News(var status : String, var totalResults : Int, var articles : List<Article>)