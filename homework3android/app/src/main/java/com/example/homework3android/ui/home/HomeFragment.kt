package com.example.homework3android.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework3android.R
import com.example.homework3android.data.dto.HomeDTO
import com.example.homework3android.data.model.PostModel
import com.example.homework3android.data.model.UserModel
import com.example.homework3android.databinding.FragmentHomeBinding
import com.example.homework3android.ui.adapters.HomeMainAdapter


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels { HomeViewModel.Factory }
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeMainAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.state.observe(this.viewLifecycleOwner){
            render(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleViewInit()

    }

    private fun render(state: HomeState){
        when(state){
            is HomeState.Success -> showData(state.data)
            HomeState.Load -> showProgressBar()
        }
    }

    private fun showProgressBar() {
        binding.homeProgressBar.visibility = View.VISIBLE
    }

    private fun showData(dto:HomeDTO){
        binding.homeProgressBar.visibility = View.GONE
        val artists = dto.users
        val posts = dto.posts
        adapter.setData(artists, randomFilters, posts)
    }

    private fun recycleViewInit(){
        adapter = HomeMainAdapter()
        binding.HomeRecycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.HomeRecycleView.adapter = adapter
    }

    private val randomFilters = listOf("Today", "On last week", "blog", "aeryn", "juehf")

}