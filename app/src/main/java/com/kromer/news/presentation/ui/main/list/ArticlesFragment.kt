package com.kromer.news.presentation.ui.main.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.kromer.news.R
import com.kromer.news.databinding.FragmentArticlesBinding
import com.kromer.news.domain.model.Article
import com.kromer.news.extensions.hide
import com.kromer.news.extensions.show
import com.kromer.news.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ExperimentalPagingApi
@AndroidEntryPoint
class ArticlesFragment : BaseFragment<FragmentArticlesBinding>() {

    private val viewModel: ArticlesViewModel by viewModels()
    private lateinit var adapter: ArticlesAdapter

    private var pageSize = 20
    private var searchQuery = "apple"

    override fun getVBInflater(): (LayoutInflater) -> FragmentArticlesBinding =
        FragmentArticlesBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        request()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        val item: MenuItem = menu.findItem(R.id.action_search)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
        val searchView: SearchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchQuery = query!!
                request()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchQuery = newText!!
                request()
                return true
            }
        })
    }

    private fun setupObservers() {
        viewModel.getArticles(pageSize).observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        // Activities can use lifecycleScope directly, but Fragments should instead use
        // viewLifecycleOwner.lifecycleScope.
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> {
                        showLoading()
                    }
                    is LoadState.Error -> {
                        showError(getString(R.string.no_data))
                    }
                    else -> {
                        showData()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = ArticlesAdapter(
            itemClick = { onItemClick(it) }
        )
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun onItemClick(item: Article) {
        val action = ArticlesFragmentDirections.actionNavigationArticlesToDetails(item)
        findNavController().navigate(action)
    }

    private fun showLoading() {
        binding.progressBar.show()
    }

    private fun showError(error: String) {
        binding.recyclerView.hide()
        binding.progressBar.hide()
        binding.textView.show()

        binding.textView.text = error
    }

    private fun showData() {
        binding.recyclerView.show()
        binding.progressBar.hide()
        binding.textView.hide()
    }

    private fun reset() {
        binding.recyclerView.hide()
        binding.progressBar.show()
        binding.textView.hide()
    }

    private fun getData(q: String) {
        viewModel.search(q)
    }

    private fun request() {
        reset()
        getData(searchQuery)
    }
}