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
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleViewInit()

    }

    private fun render(state: HomeState){
        when(state){
            is HomeState.Success -> showData()
            HomeState.Load -> showProgressBar()
        }
    }

    private fun showProgressBar() {
        binding.homeProgressBar.visibility = View.VISIBLE
    }

    private fun showData(){
        binding.homeProgressBar.visibility = View.GONE
    }

    private fun recycleViewInit(){
        adapter = HomeMainAdapter(randomUserModels, randomFilters, randomPostModels)
        binding.HomeRecycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.HomeRecycleView.adapter = adapter
    }

    private val randomPostModels = listOf(
        PostModel(title = "azpmanvuaj", description = "itfstqpxpxvswbpvtpth", image = "https://api.adorable.io/avatars/926", likes = 23, chats = 22, author = 4L),
        PostModel(title = "bobsxvzgbb", description = "tampmyikkkpwmtanmjyz", image = "https://api.adorable.io/avatars/184", likes = 80, chats = 29, author = 2L),
        PostModel(title = "edyyulifyh", description = "dhzhdkfgjipygcgemudi", image = "https://api.adorable.io/avatars/561", likes = 18, chats = 17, author = 2L),
        PostModel(title = "nbduheiwhf", description = "ztaieeiaqdkaxkyhtplt", image = "https://api.adorable.io/avatars/367", likes = 81, chats = 27, author = 1L),
        PostModel(title = "wngxksuvny", description = "wlxpasorqgqmoleulzjs", image = "https://api.adorable.io/avatars/741", likes = 94, chats = 0, author = 3L)
    )


    private val randomUserModels = listOf(
        UserModel(id = 0L, username = "aposavtn", avatar = "https://api.adorable.io/avatars/516"),
        UserModel(id = 1L, username = "urxcaytz", avatar = "https://api.adorable.io/avatars/221"),
        UserModel(id = 2L, username = "ovwsebkv", avatar = "https://api.adorable.io/avatars/427"),
        UserModel(id = 3L, username = "zmdartgz", avatar = "https://api.adorable.io/avatars/750"),
        UserModel(id = 4L, username = "vhizrjci", avatar = "https://api.adorable.io/avatars/604")
    )

    private val randomFilters = listOf("ttams", "thcab", "nwpgf", "aeryn", "juehf")





}