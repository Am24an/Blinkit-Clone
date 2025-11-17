package com.aman.userblinkit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aman.userblinkit.Constants
import com.aman.userblinkit.R
import com.aman.userblinkit.adapters.AdapterCategory
import com.aman.userblinkit.databinding.FragmentHomeBinding
import com.aman.userblinkit.models.Category

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setStatusBarColor()
        setAllCategories()
        navigatingToSearchFragment()

        return binding.root
    }

    private fun navigatingToSearchFragment() {
        binding.searchCv.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun setAllCategories() {
        val categoryList = ArrayList<Category>()

        for (i in 0 until Constants.allProductsCategoryIcon.size) {
            categoryList.add(
                Category(
                    Constants.allProductsCategory[i],
                    Constants.allProductsCategoryIcon[i]
                )
            )
        }

        binding.rvCategories.adapter = AdapterCategory(categoryList)
    }


    private fun setStatusBarColor() {
        activity?.window?.let { window ->
            // Set status bar color (works on API 21+)
            window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.orange)

            // Use WindowInsetsControllerCompat for icon color control
            val wic = WindowInsetsControllerCompat(window, window.decorView)
            // true = dark icons (for light background), false = light icons
            wic.isAppearanceLightStatusBars = true
            WindowCompat.setDecorFitsSystemWindows(window, true)
        }
    }
}