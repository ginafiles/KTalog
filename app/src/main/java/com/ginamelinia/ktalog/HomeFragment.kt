package com.ginamelinia.ktalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ginamelinia.ktalog.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var genreRecyclerView: RecyclerView
    private lateinit var dramaRecyclerView: RecyclerView
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var dramaAdapter: DramaAdapter
    private lateinit var homeViewModel: HomeViewModel

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
        dramaRecyclerView = binding.kdramaRecyclerView

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.genreList.observe(viewLifecycleOwner, Observer { genreList ->
            genreList?.let {
                val firstFourGenres = genreList.take(4)
                genreAdapter = GenreAdapter()
                genreAdapter.submitList(firstFourGenres)
                genreRecyclerView.adapter = genreAdapter

                val genreLayoutManager = GridLayoutManager(requireContext(), 2)
                genreRecyclerView.layoutManager = genreLayoutManager
            }
        })

        homeViewModel.dramaList.observe(viewLifecycleOwner, Observer { dramaList ->
            dramaList?.let {
                dramaAdapter = DramaAdapter()
                dramaAdapter.submitList(dramaList)
                dramaRecyclerView.adapter = dramaAdapter

                val dramaLayoutManager = LinearLayoutManager(requireContext())
                dramaRecyclerView.layoutManager = dramaLayoutManager
            }
        })

        homeViewModel.loadGenres(requireContext())
        homeViewModel.loadDramas(requireContext())


        binding.btnSeeAll.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToGenreFragment()
            findNavController().navigate(action)
        }
    }
}
