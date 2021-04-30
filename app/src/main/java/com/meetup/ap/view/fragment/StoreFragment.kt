package com.meetup.ap.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.meetup.ap.R
import com.meetup.ap.`interface`.ItemInterface
import com.meetup.ap.databinding.FragmentStoreBinding
import com.meetup.ap.view.epoxy.controller.MainController

class StoreFragment : Fragment() {

    private val viewModel: StoreViewModel by viewModels()
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf("dad", "mom", "son", "daughter")
        val navController = findNavController()
        val controller = MainController(object : ItemInterface {
            override fun onItemClick(item: String) {
                navController.navigate(R.id.action_storeFragment_to_informationFragment)
            }
        })
        binding.recyclerView.setController(controller)
        controller.submit(list)
    }
}