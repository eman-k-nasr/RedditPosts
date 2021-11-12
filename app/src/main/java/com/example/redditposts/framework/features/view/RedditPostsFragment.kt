package com.example.redditposts.framework.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.annotation.ExperimentalCoilApi
import com.example.redditposts.business.entities.response.Post
import com.example.redditposts.business.entities.state.UiState
import com.example.redditposts.business.utils.Constants.Companion.PAGE_SIZE
import com.example.redditposts.framework.components.CircularProgressBar
import com.example.redditposts.framework.components.RedditPostCard
import com.example.redditposts.framework.components.SearchBar
import com.example.redditposts.framework.features.viewmodel.RedditPostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalUnitApi
@ExperimentalCoilApi
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
                val page = redditPostsViewModel.page.value
                val query = redditPostsViewModel.query.value
                Column() {
                    SearchBar(
                       query = query,
                       onQueryChanged = redditPostsViewModel::onQueryChanged,
                       onExecuteSearch = redditPostsViewModel::searchRedditPosts
                    )
                    HandleUiState(response, page)
                }
            }
        }
    }

    @Composable
    private fun HandleUiState(it: UiState<List<Post>>, page: Int) {
        when (it) {
            is UiState.Loading -> CircularProgressBar(isLoading = it.isLoading)
            is UiState.Success -> RedditPostRecyclerView(it.data, page)
            is UiState.Error -> showToast(it.msg)
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    @Composable
    fun RedditPostRecyclerView(list: List<Post>, page: Int) {
        LazyColumn {
            itemsIndexed(items = list) { index, post ->
                redditPostsViewModel.onChangeScrollPosition(index)
                if ((index + 1) >= (page * PAGE_SIZE)) {
                    redditPostsViewModel.nextPage()
                }
                RedditPostCard(post = post, onClick = {})
            }
        }
    }

}
