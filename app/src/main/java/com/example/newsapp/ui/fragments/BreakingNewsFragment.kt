package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentBreakingNewsBinding
import com.example.newsapp.model.response.Article
import com.example.newsapp.utils.Resource
import com.example.newsapp.viewModels.NewsViewModels

class BreakingNewsFragment : Fragment() {
    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsViewModels
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBreakingNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModels::class.java]
        setupAdapter()
        breakingNewsApi()
    }

    private fun setupAdapter() {
        newsAdapter = NewsAdapter { pos, model, src ->
            clickOnArticle(pos, model, src)
        }
        binding.rvBreakingNews.adapter = newsAdapter
    }

    private fun clickOnArticle(pos: Int, model: Article, src: String) {
        when (src) {
            "root" -> {
                val bundle = Bundle().apply {
                    putParcelable("article", model)
                }
                findNavController().navigate(R.id.action_breakingNewsFragment_to_newsArticleFragment,bundle)
            }
        }
    }

    private fun breakingNewsApi() {
        viewModel.breakingNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.paginationProgressBar.visibility = View.INVISIBLE
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    binding.paginationProgressBar.visibility = View.INVISIBLE
                    response.message?.let { message ->
                        Log.e("Breaking News", "$message")
                    }
                }

                is Resource.InternetError -> {}
                is Resource.Loading -> {
                    binding.paginationProgressBar.visibility = View.VISIBLE
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
