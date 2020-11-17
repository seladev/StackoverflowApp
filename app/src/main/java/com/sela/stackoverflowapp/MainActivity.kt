package com.sela.stackoverflowapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sela.stackoverflowapp.ui.BaseActivity

class MainActivity : BaseActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override var resourceLayout = R.layout.activity_main

}