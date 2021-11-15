package com.vmh.barberapp.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.vmh.barberapp.R
import com.vmh.barberapp.utils.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        private const val TAG = "MainActivity"

        fun instance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
