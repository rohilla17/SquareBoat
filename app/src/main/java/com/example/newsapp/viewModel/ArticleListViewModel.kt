package com.example.newsapp.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.view.ArticleAdapter
import com.example.newsapp.model.News
import com.example.newsapp.network.NewsApi
import com.example.newsapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import com.example.newsapp.model.Article


class ArticleListViewModel: BaseViewModel(){

    @Inject
    lateinit var newsApi: NewsApi
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val articleListAdapter: ArticleAdapter =
        ArticleAdapter()
    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = newsApi.getHeadlines("IN", "business",
            "adb33825dd35457689c049ce7c90173a")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribeWith(object : DisposableObserver<News>(){

                override fun onNext(t: News) {
                    onRetrievePostListSuccess(t.articles)
                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    Log.e("ERROR", e.message!!)
                    onRetrievePostListError()
                }

            })
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE

    }

    private fun onRetrievePostListSuccess(postList:List<Article>){
        articleListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}