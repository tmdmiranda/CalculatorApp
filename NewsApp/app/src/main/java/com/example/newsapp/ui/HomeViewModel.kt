package com.example.newsapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newsapp.Models.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


data class ArticlesState(
    var articles: ArrayList<Article> = arrayListOf(),
    var isLoading: Boolean = false,
    var error: String? = null
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesState())
    val uiState: StateFlow<ArticlesState> = _uiState.asStateFlow()

    fun fetchArticles() {
        _uiState.value = ArticlesState(isLoading = true, error = null)

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=tesla&from=2024-09-16&sortBy=publishedAt&apiKey=54a3c2c292ae4ff1a120f30b2e0e0abd")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                _uiState.value = ArticlesState(isLoading = true, error = null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val articlesResult = arrayListOf<Article>()
                    val result = response.body!!.string()
                    val jsonResult = JSONObject(result)
                    val status = jsonResult.getString("status")
                    if (status == "ok") {
                        val articlesJson = jsonResult.getJSONArray("articles")
                        for (index in 0 until articlesJson.length()) {
                            val articleJson = articlesJson.getJSONObject(index)
                            val article = Article.fromJson(articleJson)
                            articlesResult.add(article)
                        }
                    }
                    _uiState.value =
                        ArticlesState(articles = articlesResult, isLoading = true, error = null)
                }
            }
        })
    }
}
