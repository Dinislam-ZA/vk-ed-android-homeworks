package com.example.homework3android.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework3android.R
import com.example.homework3android.data.dto.HomeDTO
import com.example.homework3android.data.model.PostModel
import com.example.homework3android.data.model.PostVO
import com.example.homework3android.data.model.UserModel
import com.example.homework3android.databinding.FragmentHomeBinding
import com.example.homework3android.ui.adapters.HomeMainAdapter
import com.example.homework3android.ui.adapters.HomeMainListener


class HomeFragment : Fragment(), HomeMainListener {

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

        val randomFilters = listOf(
            getString(R.string.filter_all),
            getString(R.string.filter_on_last_week),
            getString(R.string.filter_today),
            getString(R.string.filter_only_art),
            getString(R.string.filter_only_blog)
        )

        recycleViewInit(randomFilters)

    }

    private fun render(state: HomeState){
        when(state){
            is HomeState.Success -> showData(state.data)
            HomeState.Load -> showProgressBar()
        }
    }

    private fun showProgressBar() {
        binding.HomeRecycleView.visibility = View.GONE
        binding.homeProgressBar.visibility = View.VISIBLE
    }

    private fun showData(dto:HomeDTO){
        binding.homeProgressBar.visibility = View.GONE
        binding.HomeRecycleView.visibility = View.VISIBLE
        val artists = dto.users
        val posts = dto.posts
        adapter.setData(artists, posts)
    }

    private fun recycleViewInit(filters:List<String>){
        adapter = HomeMainAdapter(this, filters)
        binding.HomeRecycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.HomeRecycleView.adapter = adapter
    }



    override fun onMoreButtonClick(post: PostVO) {
        val action = HomeFragmentDirections.actionHomeFragmentToPostFragment(post)
        findNavController().navigate(action)
    }

}