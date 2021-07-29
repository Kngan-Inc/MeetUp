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
import com.meetup.app.databinding.FragmentSignInBinding
import com.meetup.app.view.epoxy.controller.MainController

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: FragmentSignInBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val navController = findNavController()

//        binding.signInButton.setOnClickListener {
//            navController.navigate(R.id.action_signInFragment_to_OTPFragment)
//        }

        val controller = MainController(object: ItemInterface {
            override fun onItemClick(item: String) {
                navController.navigate(R.id.action_signInFragment_to_OTPFragment)
            }
        })
        controller.submit(list = arrayListOf("hi"))

        binding.recyclerview.setController(controller)
    }

}