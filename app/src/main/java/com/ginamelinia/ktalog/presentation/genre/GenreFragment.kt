package com.ginamelinia.ktalog.presentation.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ginamelinia.ktalog.R
import com.ginamelinia.ktalog.databinding.FragmentGenreBinding
import com.ginamelinia.ktalog.presentation.adapter.genre.GenreAdapter

class GenreFragment : Fragment() {
    private lateinit var binding: FragmentGenreBinding
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var genreRecyclerView: RecyclerView
    private lateinit var genreViewModel: GenreViewModel // Buat GenreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genreRecyclerView = binding.genreRecyclerView

        genreViewModel = ViewModelProvider(this).get(GenreViewModel::class.java)
        genreViewModel.genreList.observe(viewLifecycleOwner, Observer { genreList ->
            genreList?.let {
                val genres = genreList
                genreAdapter = GenreAdapter()
                genreAdapter.submitList(genres)
                genreRecyclerView.adapter = genreAdapter

                val genreLayoutManager = GridLayoutManager(requireContext(), 2)
                genreRecyclerView.layoutManager = genreLayoutManager
            }
        })

        genreViewModel.loadGenres(requireContext())

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_genreFragment_to_homeFragment)
        }
    }
}
