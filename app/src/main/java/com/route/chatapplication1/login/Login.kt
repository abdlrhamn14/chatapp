package com.route.chatapplication1.login
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chatapplication1.NavigateToView
import com.route.chatapplication1.databinding.ActivityLoginBinding
import com.route.chatapplication1.home.Home
import com.route.chatapplication1.register.Register
import com.route.chatapplication1.showDialog



class Login : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewBinding: ActivityLoginBinding

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
                posAction = message.posAction,
                negAction = message.negAction,
                isCancelable = message.isCancelable

            )
        }
        viewModel.navigateToView.observe(this) { order ->
            when (order) {
                NavigateToView.NavigateToHome -> {
                    navigateToHome()
                }

                NavigateToView.NavigateToLogin -> {
                    navigateToRegister()
                }
            }
        }
    }

    private fun navigateToRegister() {
        val intent=Intent(this, Register::class.java)
        startActivity(intent)
        finish()
    }

    private fun initViews() {
            viewBinding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(viewBinding.root)
            viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
            viewBinding.lifecycleOwner = this
            viewBinding.vm = viewModel
            viewBinding.content.doNotHaveAccount.setOnClickListener {
                var intent = Intent(this, Register::class.java)
                startActivity(intent)
            }
        }



    private fun navigateToHome() {
        val intent=Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}