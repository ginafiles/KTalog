package com.ginamelinia.ktalog.presentation.drama

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ginamelinia.ktalog.HomeViewModel
import com.ginamelinia.ktalog.R
import com.ginamelinia.ktalog.data.model.Genre
import com.ginamelinia.ktalog.data.model.RetrofitClient
import com.ginamelinia.ktalog.data.remote.RemoteRepository
import com.ginamelinia.ktalog.data.remote.service.TMDBApiService
import com.ginamelinia.ktalog.databinding.FragmentDramaBinding
import com.ginamelinia.ktalog.di.ViewModelFactory
import com.ginamelinia.ktalog.presentation.adapter.drama.DramaAdapter

class DramaFragment : Fragment() {

    private lateinit var binding: FragmentDramaBinding
    private lateinit var dramaRecyclerView: RecyclerView
    private lateinit var dramaAdapter: DramaAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDramaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dramaRecyclerView = binding.dramaRecyclerView

        val retrofit = RetrofitClient.create(requireContext())
        val apiService = retrofit.create(TMDBApiService::class.java)
        val repository = RemoteRepository(apiService)
        val viewModelFactory = ViewModelFactory(
            homeRepository = repository
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        val selectedGenre = arguments?.get("selectedGenre") as? Genre
        selectedGenre?.let {
            activity?.title = it.genreTitle
            viewModel.loadDramasByGenre(it.id)
        }

        viewModel.dramaList.observe(viewLifecycleOwner, Observer { dramaList ->
            dramaList?.let {
                dramaAdapter = DramaAdapter{ item ->
                    val action = DramaFragmentDirections.actionDramaFragmentToDramaDetailFragment(item)
                    findNavController().navigate(action)
                }
                dramaAdapter.submitList(dramaList)
                dramaRecyclerView.adapter = dramaAdapter

                val dramaLayoutManager = LinearLayoutManager(requireContext())
                dramaRecyclerView.layoutManager = dramaLayoutManager
            }
        })

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_dramaFragment_to_genreFragment)
        }
    }
}
