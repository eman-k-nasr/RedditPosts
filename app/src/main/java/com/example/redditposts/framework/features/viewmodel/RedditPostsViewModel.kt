package com.example.redditposts.framework.features.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redditposts.business.entities.response.Post
import com.example.redditposts.business.entities.response.RedditResponse
import com.example.redditposts.business.entities.state.UiState
import com.example.redditposts.business.repository.abstraction.RedditPostsRepository
import com.example.redditposts.business.utils.Constants.Companion.INITIAL_SEARCH_QUERY
import com.example.redditposts.business.utils.Constants.Companion.INITIAL_STARTING_POINT
import com.example.redditposts.business.utils.Constants.Companion.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RedditPostsViewModel @Inject constructor(private val repo: RedditPostsRepository) :
    ViewModel() {
    var redditPostsUiState: MutableState<UiState<List<Post>>> =
        mutableStateOf(UiState.Empty)

    private var startingPoint = mutableStateOf(INITIAL_STARTING_POINT)
    val query = mutableStateOf(INITIAL_SEARCH_QUERY)
    var page = mutableStateOf(1)
    private var scrollPosition = 0
    private  val currentList =  ArrayList<Post>()

    init {
        searchRedditPosts()
    }

    fun nextPage(){
        viewModelScope.launch {
            if((scrollPosition+1 ) >= page.value * PAGE_SIZE){
                incrementPage()
                Log.i("startingpoint","after: ${startingPoint.value}, page: ${page.value}")
                //delay
                delay(1000)
                if(page.value > 1){
                    try{
                        val response = repo.searchRedditPosts(query.value,
                            PAGE_SIZE, startingPoint.value)
                        redditPostsSuccessState(response,response.data.posts)
                    }catch(ex: Exception){
                        redditPostsErrorState(ex)
                    }
                }

            }
        }
    }

    private fun incrementPage(){
        page.value = page.value + 1
    }

    fun onChangeScrollPosition(position:Int){
        scrollPosition = position
    }


    fun onQueryChanged(query: String){
        this.query.value = query
    }

    fun searchRedditPosts() {
        redditPostsUiState.value = UiState.Loading(true)
        resetSearch()
        viewModelScope.launch {
            try {
                val response = repo.searchRedditPosts(query.value,
                    PAGE_SIZE, startingPoint.value)
                redditPostsSuccessState(response,response.data.posts)
            } catch (ex: Exception) {
                redditPostsErrorState(ex)
            }
        }
    }

    private fun resetSearch(){
        startingPoint.value = INITIAL_STARTING_POINT
        currentList.clear()
        page.value = 1
        onChangeScrollPosition(0)
    }

    private fun redditPostsSuccessState(redditResponse: RedditResponse,newList: List<Post>){
        startingPoint.value = redditResponse.data.after
        Log.i("startingpoint",startingPoint.value)
        redditPostsUiState.value = UiState.Loading(false)
        currentList.addAll(newList)
        redditPostsUiState.value = UiState.Success(currentList)
    }
    private fun redditPostsErrorState(ex:Exception){
        redditPostsUiState.value = UiState.Loading(false)
        redditPostsUiState.value =
            UiState.Error(ex.message ?: "some error..")
    }
}