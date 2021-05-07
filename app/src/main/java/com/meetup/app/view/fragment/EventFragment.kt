package com.meetup.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.meetup.app.R
import com.meetup.app.`interface`.ItemInterface
import com.meetup.app.databinding.FragmentEventBinding
import com.meetup.app.view.epoxy.controller.MainController

class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private val viewModel: EventViewModel by viewModels()
    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentEventBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf("dad", "mom", "son", "daughter")
        val navController = findNavController()

        val controller = MainController(object : ItemInterface {
            override fun onItemClick(item: String) {
                navController.navigate(R.id.action_homeFragment2_to_informationFragment)
            }
        })

        binding.recyclerView.setController(controller)
        controller.submit(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}