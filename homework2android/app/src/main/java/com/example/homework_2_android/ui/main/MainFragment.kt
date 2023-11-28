package com.example.homework_2_android.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homework_2_android.R
import com.example.homework_2_android.data.model.Gif
import com.example.homework_2_android.databinding.FragmentMainBinding
import com.example.homework_2_android.ui.adapters.GifAdapter
import com.example.homework_2_android.ui.adapters.ReloadButtonClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainFragment : Fragment(), ReloadButtonClickListener {

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
            render(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecycleView()
    }

    private fun render(state: MainState){
        when(state){
            is MainState.Loading -> showLoad()
            is MainState.Success -> showGifs(state.gifs)
            is MainState.Error -> showError(state.message)
            is MainState.SuccessReload -> reload(state.gifs)
        }
    }

    private fun reload(gifs: List<Gif>?) {
        adapter.setListItem(gifs!!)
    }


    private fun showGifs(gifs: List<Gif>?) {
        //binding.progressBar.visibility = View.GONE
        adapter.removeLoadingOrErrorState()
        Log.d("gifs in fragment", gifs.toString())
        gifs?.let { adapter.setListItem(it) }
    }

    private fun showLoad(){
//        binding.progressBar.visibility = View.VISIBLE
        Log.d("func", "showLoad")
        adapter.showLoadingBar()
    }

    private fun showError(errorMessage: String){
//        val dialog = context?.let {
//            MaterialAlertDialogBuilder(it)
//                .setTitle(getString(R.string.load_error_message))
//                .setMessage(errorMessage)
//                .setPositiveButton(getString(R.string.load_error_dialog_button_text)) { dialog, which ->
//                    viewModel.processIntent(MainIntent.ResetGifs)
//                }
//                .create()
//        }
//
//        dialog?.show()
        Log.d("func", "showError")
        adapter.showReloadButton()
    }

    private fun initializeRecycleView(){
        adapter = GifAdapter(this)
        with(binding){
            gifRecycleView.layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            gifRecycleView.adapter = adapter
            gifRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (!recyclerView.canScrollVertically(1) && dy > 0 && (viewModel.state.value !is MainState.Error)) {
                        viewModel.processIntent(MainIntent.LoadGifs)
                    }
                }
            })
        }
    }

    override fun onClickReload() {
        viewModel.processIntent(MainIntent.ResetGifs)
    }

}