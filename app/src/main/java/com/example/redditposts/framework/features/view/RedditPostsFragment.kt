package com.example.redditposts.framework.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.redditposts.business.datasource.remote.implementation.RedditPostsDataSourceImpl
import com.example.redditposts.business.datasource.remote.retrofit.RetrofitBuilder
import com.example.redditposts.business.datasource.remote.services.RedditPostsService
import com.example.redditposts.business.entities.response.Post
import com.example.redditposts.business.entities.state.UiState
import com.example.redditposts.business.repository.implementation.RedditPostsRepositoryImpl
import com.example.redditposts.framework.features.viewmodel.RedditPostsViewModel
import com.example.redditposts.framework.utils.RedditPostsViewModelFactory

class RedditPostsFragment : Fragment() {
    private lateinit var redditPostsViewModel: RedditPostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                Text("Reddit Posts Fragment ..")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        sendRequests()
        setUpObservers()

    }

    private fun setUpViewModel(){
        val viewModelFactory = RedditPostsViewModelFactory(
            RedditPostsRepositoryImpl(RedditPostsDataSourceImpl(
                RetrofitBuilder.buildService(RedditPostsService::class.java)
            ))
        )
        redditPostsViewModel = ViewModelProvider(this,viewModelFactory)
            .get(RedditPostsViewModel::class.java)
    }

    private fun sendRequests(){
        redditPostsViewModel.getRedditPostsRepository("all",10,"poo")
        redditPostsViewModel.searchRedditPostsRepository("math",10,"poo")
    }

    private fun setUpObservers(){
        redditPostsViewModel.redditPostsUiState.observe(viewLifecycleOwner,{
            handleUiState(it)
        })

        redditPostsViewModel.searchRedditPostsUiState.observe(viewLifecycleOwner,{
            handleUiState(it)
        })
    }
    private fun handleUiState(it: UiState<List<Post>>){
        when(it){
            is UiState.Loading -> showToast("loading")
            is UiState.Success -> showToast(it.data.first().postData.title)
            is UiState.Error -> showToast(it.msg)
        }
    }
    private fun showToast(text:String){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
    }
}