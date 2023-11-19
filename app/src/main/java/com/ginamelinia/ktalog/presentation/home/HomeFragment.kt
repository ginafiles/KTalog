package com.ginamelinia.ktalog.presentation.home

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
import com.ginamelinia.ktalog.HomeViewModel
import com.ginamelinia.ktalog.data.model.RetrofitClient
import com.ginamelinia.ktalog.data.remote.RemoteRepository
import com.ginamelinia.ktalog.data.remote.service.TMDBApiService
import com.ginamelinia.ktalog.databinding.FragmentHomeBinding
import com.ginamelinia.ktalog.di.ViewModelFactory
import com.ginamelinia.ktalog.presentation.adapter.drama.DramaAdapter
import com.ginamelinia.ktalog.presentation.adapter.genre.GenreAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    // recycler view
    private lateinit var genreRecyclerView: RecyclerView
    private lateinit var dramaRecyclerView: RecyclerView

    // adapter
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var dramaAdapter: DramaAdapter

    // view model
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

        val retrofit = RetrofitClient.create(requireContext())
        val apiService = retrofit.create(TMDBApiService::class.java)
        val repository = RemoteRepository(apiService)
        val viewModelFactory = ViewModelFactory(
            homeRepository = repository
        )

        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        homeViewModel.genreList.observe(viewLifecycleOwner, Observer { genreList ->
            genreList?.let {
                val firstFourGenres = genreList.take(4)
                genreAdapter = GenreAdapter { genre ->
                    val action = HomeFragmentDirections.actionHomeFragmentToDramaFragment(genre)
                    findNavController().navigate(action)
                }
                genreAdapter.submitList(firstFourGenres)
                genreRecyclerView.adapter = genreAdapter

                val genreLayoutManager = GridLayoutManager(requireContext(), 2)
                genreRecyclerView.layoutManager = genreLayoutManager
            }
        })

        homeViewModel.dramaList.observe(viewLifecycleOwner, Observer { dramaList ->
            dramaList?.let {
                dramaAdapter = DramaAdapter{ item ->
                    val action = HomeFragmentDirections.actionHomeFragmentToDramaDetailFragment(item)
                    findNavController().navigate(action)
                }
                dramaAdapter.submitList(dramaList)
                dramaRecyclerView.adapter = dramaAdapter

                val dramaLayoutManager = LinearLayoutManager(requireContext())
                dramaRecyclerView.layoutManager = dramaLayoutManager
            }
        })

        homeViewModel.loadGenres()
        homeViewModel.loadDramas()

        binding.btnSeeAll.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToGenreFragment()
            findNavController().navigate(action)
        }
    }
}

