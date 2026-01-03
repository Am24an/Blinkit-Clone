 package com.aman.userblinkit.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aman.userblinkit.CartListener
import com.aman.userblinkit.R
import com.aman.userblinkit.databinding.ActivityUsersMainBinding

 class UsersMainActivity : AppCompatActivity(), CartListener {

    private lateinit var binding: ActivityUsersMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUsersMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun showCartLayout(itemCount: Int) {
        val previousCount = binding.tvNumOfProductCount.text.toString().toInt()
        val updatedCount = previousCount + itemCount

        if(updatedCount > 0){
            binding.llCart.visibility = View.VISIBLE
            binding.tvNumOfProductCount.text = updatedCount.toString()
        }
        else{
            binding.llCart.visibility = View.GONE
            binding.tvNumOfProductCount.text = "0"
        }
    }
}