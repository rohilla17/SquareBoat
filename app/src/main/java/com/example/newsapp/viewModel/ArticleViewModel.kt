package com.example.newsapp.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.newsapp.model.Article
import java.text.SimpleDateFormat
import java.util.*

class ArticleViewModel: BaseViewModel() {
    private val articleTitle = MutableLiveData<String>()
    private val articleImage = MutableLiveData<String>()
    private val articleDate = MutableLiveData<String>()
    private val articleSource = MutableLiveData<String>()

    fun bind(article: Article){
        articleTitle.value = article.title
        articleImage.value = article.urlToImage
        articleDate.value = article.publishedAt
        articleSource.value = article.source.name
    }

    fun getArticleTitle():MutableLiveData<String>{
        return articleTitle
    }

    fun getArticleDate():MutableLiveData<String>{
        return parseDate(articleDate)
    }

    fun getArticleSource():MutableLiveData<String>{
        return articleSource
    }

    fun getArticleImage():MutableLiveData<String>{
        return articleImage
    }

    fun parseDate(originalDate : MutableLiveData<String>) : MutableLiveData<String>{
        val date1 : MutableLiveData<String> = MutableLiveData()
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val targetFormat = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.US)
        val date = originalFormat.parse(originalDate.value.toString())
        date1.value = targetFormat.format(date!!)
        return date1
    }

}