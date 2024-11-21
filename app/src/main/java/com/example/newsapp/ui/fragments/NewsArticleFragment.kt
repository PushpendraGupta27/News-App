package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.FragmentNewsArticleBinding
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.viewModels.NewsViewModels

class NewsArticleFragment : Fragment() {
    private var _binding: FragmentNewsArticleBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNewsArticleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}