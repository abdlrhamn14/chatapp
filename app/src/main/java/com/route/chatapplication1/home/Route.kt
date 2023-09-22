package com.route.chatapplication1.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.route.chatapplication1.R

class Route : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)
    }//this is comment
    fun method(){
        Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show()
    }
    // comment for try to merge it $Mohamed
}