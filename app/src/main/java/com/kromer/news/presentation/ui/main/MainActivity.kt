package com.kromer.news.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import com.kromer.news.databinding.ActivityMainBinding
import com.kromer.news.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getVBInflater(): (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}