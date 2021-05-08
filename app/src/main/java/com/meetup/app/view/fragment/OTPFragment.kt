package com.meetup.app.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.meetup.app.R
import com.meetup.app.databinding.FragmentOtpBinding

class OTPFragment : Fragment() {

    companion object {
        fun newInstance() = OTPFragment()
    }

    private val viewModel: OTPViewModel by viewModels()
    private lateinit var binding: FragmentOtpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.phoneNumberTextField.editText?.doOnTextChanged { text, start, before, count ->

        }

        binding.button.setOnClickListener {
            if (binding.phoneNumberTextField.editText?.text!!.contains("i love you")) {
                showDialog(requireContext().getColor(R.color.blue), title = "Trov hz",description = "jng ban kuy oy slanh hehe 😍😍")

            } else {
                showDialog(requireContext().getColor(R.color.red), title = "Error Hz",description = "Check mdong tt. khos ey hz")
            }
        }
    }

    private fun showDialog(color: Int, title: String, description: String) {

        val builder = AlertDialog.Builder(requireContext(), R.style.MeetupWidgetDialog)
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.component_dialog, null)

//        view.setBackgroundColor(
//            ContextCompat.getColor(
//                requireContext(),
//                android.R.color.transparent
//            )
//        )

        builder.setView(view)
        val alertDialog = builder.create()
        alertDialog.show()
        view.findViewById<MaterialButton>(R.id.button).setOnClickListener {
            alertDialog.dismiss()
        }
        view.findViewById<MaterialTextView>(R.id.titleTextView).text = title
        view.findViewById<MaterialTextView>(R.id.titleTextView).setTextColor(color)
        view.findViewById<MaterialTextView>(R.id.descriptionTextView).text = description

    }

}