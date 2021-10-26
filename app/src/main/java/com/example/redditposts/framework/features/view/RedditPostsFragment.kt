package com.example.redditposts.framework.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.redditposts.business.entities.response.Post
import com.example.redditposts.business.entities.state.UiState
import com.example.redditposts.framework.components.RedditPostRecyclerView
import com.example.redditposts.framework.features.viewmodel.RedditPostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RedditPostsFragment : Fragment() {
    val redditPostsViewModel: RedditPostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                val response = redditPostsViewModel.redditPostsUiState.value
                HandleUiState(response)
//                val searchResponse = redditPostsViewModel.searchPostsUiState.value
//                HandleUiState(searchResponse)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendRequests()
    }

    private fun sendRequests() {
        redditPostsViewModel.getRedditPostsRepository("all", 10, "poo")
        //redditPostsViewModel.searchRedditPostsRepository("math",10,"poo")
    }


    @Composable
    private fun HandleUiState(it: UiState<List<Post>>) {
        when (it) {
            is UiState.Loading -> if(it.isLoading) showToast("loading..")
            is UiState.Success -> RedditPostRecyclerView(it.data)
            is UiState.Error -> showToast(it.msg)
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}