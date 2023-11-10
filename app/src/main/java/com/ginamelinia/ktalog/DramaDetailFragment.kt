package com.ginamelinia.ktalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ginamelinia.ktalog.databinding.FragmentDramaDetailBinding

class DramaDetailFragment : Fragment() {
    private lateinit var binding: FragmentDramaDetailBinding

//    private val args: DramaDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDramaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val selectedDrama = args.selectedDrama

//        binding.tvKdramaName.text = selectedDrama.name
//        binding.tvKdramaOverview.text = selectedDrama.overview
//
//        Glide.with(binding.imageViewKdramaDetail)
//            .load("https://image.tmdb.org/t/p/w500" + selectedDrama.posterPath)
//            .into(binding.imageViewKdramaDetail)
    }
}