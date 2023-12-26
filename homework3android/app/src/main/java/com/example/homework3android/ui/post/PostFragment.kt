package com.example.homework3android.ui.post

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homework3android.R
import com.example.homework3android.data.model.PostVO
import com.example.homework3android.databinding.FragmentPostBinding

class PostFragment : Fragment() {

    companion object {
        fun newInstance() = PostFragment()
    }

    private val args: PostFragmentArgs by navArgs()


    private val viewModel: PostViewModel by viewModels()
    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val post = args.postArg

        showProgressBar()
        Handler(Looper.getMainLooper()).postDelayed({
            hideProgressBar()
            mapData(post)
        }, 2000)

        binding.backButton.setOnClickListener {
            navBack()
        }
    }

    private fun navBack(){
        binding.root.findNavController().popBackStack()
    }

    private fun mapData(post: PostVO){
        with(binding){
            username.text = post.author
            artworkTitle.text = post.title
            artworkDescription.text = post.description
            likeCount.text = post.likes.toString()
            commentCount.text = post.chats.toString()
            Glide.with(root).load(post.image).into(artworkImage)
            Glide.with(root).load(post.avatar).into(profileImage)
        }
    }

    private fun showProgressBar() {
        binding.profileImage.visibility = View.GONE
        binding.username.visibility = View.GONE
        binding.backButton.visibility = View.GONE
        binding.rateLayout.visibility = View.GONE
        binding.subscribeButton.visibility = View.GONE
        binding.artworkImage.visibility = View.GONE
        binding.artworkTitle.visibility = View.GONE
        binding.artworkDescription.visibility = View.GONE
        binding.postProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.postProgressBar.visibility = View.GONE
        binding.profileImage.visibility = View.VISIBLE
        binding.rateLayout.visibility = View.VISIBLE
        binding.username.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
        binding.subscribeButton.visibility = View.VISIBLE
        binding.artworkImage.visibility = View.VISIBLE
        binding.artworkTitle.visibility = View.VISIBLE
        binding.artworkDescription.visibility = View.VISIBLE
    }


}