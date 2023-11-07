package com.ginamelinia.ktalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ginamelinia.ktalog.databinding.FragmentGenreBinding

class GenreFragment : Fragment(){

    private lateinit var binding: FragmentGenreBinding
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var genreRecyclerView: RecyclerView

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

        val genreList = listOf(
            Genre("Romance"),
            Genre("Comedy"),
            Genre("Drama"),
            Genre("Action"),
            Genre("Thriller"),
            Genre("Fantasy"),
            Genre("Mystery")
        )

        genreAdapter = GenreAdapter()
        genreAdapter.submitList(genreList)
        genreRecyclerView.adapter = genreAdapter

        val genreLayoutManager = GridLayoutManager(requireContext(), 2)
        genreRecyclerView.layoutManager = genreLayoutManager

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_genreFragment_to_homeFragment)
        }

    }
}