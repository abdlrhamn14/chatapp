package com.route.chatapplication1.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chatapplication1.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
   private lateinit var viewBinding:ActivityHomeBinding
   private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       initViews()
    }

    private fun initViews() {
        viewBinding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel=ViewModelProvider(this)[HomeViewModel::class.java]
        viewBinding.lifecycleOwner=this
        viewBinding.vm=viewModel

    }
}