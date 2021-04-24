package com.hb.pages.features

import android.os.Bundle
import com.hb.pages.activity.BaseActivity
import com.hb.pages.databinding.ActivityMainBinding
import com.hb.pages.extensions.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    private val layoutContainer: MainLayoutContainer by lazy {
        MainLayoutContainer(
            binding,
            viewModel,
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObservers()
        layoutContainer.init()
    }

    private fun setupObservers() {
        with(viewModel) {
            observe(backAction) { cancel() }
            observe(state) { layoutContainer.setState(it) }
        }
    }
}