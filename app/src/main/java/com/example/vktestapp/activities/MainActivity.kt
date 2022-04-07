package com.example.vktestapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.vktestapp.R
import com.example.vktestapp.viewModel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class
MainActivity : AppCompatActivity() {
    init {
        val myModule = module {

            viewModel { MainViewModel() }
        }
        startKoin {
            modules(myModule)
        }
    }
    private val viewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}