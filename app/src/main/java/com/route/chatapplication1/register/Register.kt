package com.route.chatapplication1.register


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.route.chatapplication1.NavigateToView
import com.route.chatapplication1.databinding.ActivityRegisterBinding
import com.route.chatapplication1.home.Home
import com.route.chatapplication1.login.Login
import com.route.chatapplication1.showDialog


class Register : AppCompatActivity() {
    private lateinit var viewModel:RegisterViewModel
    private  lateinit var viewBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.messageLiveData.observe(this) { message ->
            showDialog(
                errorMessageApi = message.message ?: "something went wrong",
                posActionName = "ok",
                negActionName = message.negActionName,
                posAction =  message.posAction,
                negAction = message.negAction,
                isCancelable = message.isCancelable

            )
        }
        viewModel.navigateToView.observe(this){
            order->
            when(order){
                NavigateToView.NavigateToHome->{
                 navigateToHome()
                }
                NavigateToView.NavigateToLogin->{
                    navigateToLogin()
                }
            }

        }

    }

    private fun navigateToLogin() {
        val intent=Intent(this,Login::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToHome() {
    val intent=Intent(this,Home::class.java)
      startActivity(intent)
        finish()
    }

    private fun initViews() {

        viewBinding= ActivityRegisterBinding.inflate(layoutInflater )
        setContentView(viewBinding.root)
        viewModel= ViewModelProvider(this)[RegisterViewModel::class.java]
        viewBinding.lifecycleOwner=this//to work with live data
        viewBinding.vm=viewModel
        viewBinding.content.alreadyHaveAccount.setOnClickListener {
            var intent=Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}