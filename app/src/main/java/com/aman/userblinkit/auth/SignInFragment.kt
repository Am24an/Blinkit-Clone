package com.aman.userblinkit.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aman.userblinkit.R
import com.aman.userblinkit.Utils
import com.aman.userblinkit.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        setStatusBarColor()
        getUserNumber()

        onContinueButtonClick()

        return binding.root
    }

    private fun onContinueButtonClick() {
        binding.btnContinue.setOnClickListener {
            val number = binding.etUserNumber.text.toString().trim()

            if (number.isEmpty() || number.length != 10) {
                Utils.showToast(requireContext(), "Please enter a valid number")
            } else {
                val bundle = Bundle()
                bundle.putString("number", number)
                findNavController().navigate(R.id.action_signInFragment_to_OTPFragment, bundle)
            }
        }
    }

    private fun getUserNumber() {
        binding.etUserNumber.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // Empty implementation
                }

                override fun onTextChanged(
                    number: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    val len = number?.length

                    if (len == 10) {
                        binding.btnContinue.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.green
                            )
                        )
                    } else {
                        binding.btnContinue.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.grayish
                            )
                        )
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    // Empty implementation
                }

            }
        )
    }


    private fun setStatusBarColor() {
        activity?.window?.let { window ->
            // Set status bar color (works on API 21+)
            window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.yellow)

            // Use WindowInsetsControllerCompat for icon color control
            val wic = WindowInsetsControllerCompat(window, window.decorView)
            // true = dark icons (for light background), false = light icons
            wic.isAppearanceLightStatusBars = true

            // Make sure system bars are drawn correctly, no need to add/clear flags manually now
            WindowCompat.setDecorFitsSystemWindows(window, true)
        }
    }

}