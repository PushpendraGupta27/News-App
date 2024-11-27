package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.model.response.Article
import com.example.newsapp.utils.Resource
import com.example.newsapp.viewModels.NewsViewModels
import com.google.android.material.snackbar.Snackbar

class SavedNewsFragment : Fragment() {
    private var _binding: FragmentSavedNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsViewModels
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSavedNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModels::class.java]
        setupAdapter()
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(view, "Successfully delete article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSavedNews)
        }

        savedNewsApi()
    }

    private fun setupAdapter() {
        newsAdapter = NewsAdapter { pos, model, src ->
            clickOnArticle(pos, model, src)
        }
        binding.rvSavedNews.adapter = newsAdapter
    }

    private fun clickOnArticle(pos: Int, model: Article, src: String) {
        when (src) {
            "root" -> {
                val bundle = Bundle().apply {
                    putParcelable("article", model)
                }
                findNavController().navigate(
                    R.id.action_savedNewsFragment_to_newsArticleFragment,
                    bundle
                )
            }
        }
    }

    private fun savedNewsApi() {
        viewModel.getSavedNews().observe(viewLifecycleOwner) { article ->
            newsAdapter.differ.submitList(article)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}