package com.ginamelinia.ktalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.ginamelinia.ktalog.data.model.Drama
import com.ginamelinia.ktalog.databinding.FragmentDramaDetailBinding
//import com.ginamelinia.ktalog.presentation.home.HomeViewModel

class DramaDetailFragment : Fragment() {
    private lateinit var binding: FragmentDramaDetailBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDramaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val selectedDrama = arguments?.get("selectedDrama") as? Drama

        if (selectedDrama != null) {
            binding.tvKdramaName.text = selectedDrama.name
            binding.tvKdramaOverview.text = selectedDrama.overview

            Glide.with(binding.imageViewKdramaDetail)
                .load("https://image.tmdb.org/t/p/w500" + selectedDrama.posterPath)
                .into(binding.imageViewKdramaDetail)
        } else {
            Log.e("DramaDetailFragment", "Argumen selectedDrama null")
        }
    }
}