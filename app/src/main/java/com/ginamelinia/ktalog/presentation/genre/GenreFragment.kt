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
import com.ginamelinia.ktalog.HomeViewModel
import com.ginamelinia.ktalog.R
import com.ginamelinia.ktalog.data.model.RetrofitClient
import com.ginamelinia.ktalog.data.remote.RemoteRepository
import com.ginamelinia.ktalog.data.remote.service.TMDBApiService
import com.ginamelinia.ktalog.databinding.FragmentGenreBinding
import com.ginamelinia.ktalog.di.ViewModelFactory
import com.ginamelinia.ktalog.presentation.adapter.genre.GenreAdapter

class GenreFragment : Fragment() {
    private lateinit var binding: FragmentGenreBinding
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var genreRecyclerView: RecyclerView
    private lateinit var viewModel: HomeViewModel

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

        val retrofit = RetrofitClient.create(requireContext())
        val apiService = retrofit.create(TMDBApiService::class.java)
        val repository = RemoteRepository(apiService)
        val viewModelFactory = ViewModelFactory(
            homeRepository = repository
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.genreList.observe(viewLifecycleOwner, Observer { genreList ->
            genreList?.let {

                genreAdapter = GenreAdapter { genre ->
                    val action = GenreFragmentDirections.actionGenreFragmentToDramaFragment(genre)
                    findNavController().navigate(action)
                }
                genreAdapter.submitList(genreList)
                genreRecyclerView.adapter = genreAdapter

                val genreLayoutManager = GridLayoutManager(requireContext(), 2)
                genreRecyclerView.layoutManager = genreLayoutManager
            }
        })

        viewModel.loadGenres()

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_genreFragment_to_homeFragment)
        }
    }
}
