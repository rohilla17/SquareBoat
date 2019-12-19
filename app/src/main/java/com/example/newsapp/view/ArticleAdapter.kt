package com.example.newsapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.ArticleItemBinding
import com.example.newsapp.model.Article
import com.example.newsapp.viewModel.ArticleViewModel

public class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.PostViewHolder>(){
    private lateinit var articleList:List<Article>

    override fun getItemCount(): Int {
        return if(::articleList.isInitialized) articleList.size else 0
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(articleList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding: ArticleItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.article_item, parent, false)
        return PostViewHolder(binding)
    }

    fun updatePostList(postList:List<Article>){
        this.articleList = postList
        notifyDataSetChanged()
    }

    inner class PostViewHolder(val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ArticleViewModel()

        fun bind(item: Article) {
            viewModel.bind(item)
            binding.viewModel = viewModel
        }
    }
}

