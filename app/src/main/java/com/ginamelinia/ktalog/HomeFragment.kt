package com.ginamelinia.ktalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ginamelinia.ktalog.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var genreRecyclerView: RecyclerView
    private lateinit var genreAdapter: GenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
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

        val firstFourGenres = genreList.subList(0, 4)

        genreAdapter = GenreAdapter()
        genreAdapter.submitList(firstFourGenres)
        genreRecyclerView.adapter = genreAdapter

        val genreLayoutManager = GridLayoutManager(requireContext(), 2)
        genreRecyclerView.layoutManager = genreLayoutManager

        binding.btnSeeAll.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToGenreFragment()
            findNavController().navigate(action)
        }
    }
}