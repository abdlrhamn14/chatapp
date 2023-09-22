package com.route.chatapplication1

import androidx.databinding.BindingAdapter

import com.google.android.material.textfield.TextInputLayout
@BindingAdapter("app:error")
fun bindError(textInputLayout:TextInputLayout,message:String?){
textInputLayout.error=message
}