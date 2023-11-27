package com.example.homework_2_android.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homework_2_android.R
import com.example.homework_2_android.data.model.Gif
import com.example.homework_2_android.databinding.FragmentMainBinding
import com.example.homework_2_android.ui.adapters.GifAdapter
import com.example.homework_2_android.ui.adapters.GifLoadStateAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel:MainViewModel by viewModels { MainViewModel.Factory }
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: GifAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModel.state.observe(this.viewLifecycleOwner){
            when(it){
                is MainState.Loading -> showLoad()
                is MainState.Success -> showGifs(it.gifs)
                is MainState.Error -> showError(it.message)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecycleView()
    }


    private fun showGifs(gifs: PagingData<Gif>) {
        Log.d("State", "Success")
        binding.progressBar.visibility = View.GONE
        lifecycleScope.launch {
            adapter.submitData(gifs)
        }
    }

    private fun showLoad(){
        binding.progressBar.visibility = View.VISIBLE
        Log.d("State", "Loading")
    }

    private fun showError(errorMessage: String){
        val dialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.load_error_message))
                .setMessage(errorMessage)
                .setPositiveButton(getString(R.string.load_error_dialog_button_text)) { dialog, which ->
                    viewModel.processIntent(MainIntent.LoadGifs)
                }
                .create()
        }

        dialog?.show()
    }

    private fun initializeRecycleView(){
        binding.gifRecycleView.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        adapter = GifAdapter()
        val loadStateAdapter = GifLoadStateAdapter { adapter.retry() }
        binding.gifRecycleView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = loadStateAdapter,
            footer = loadStateAdapter
        )
    }

}