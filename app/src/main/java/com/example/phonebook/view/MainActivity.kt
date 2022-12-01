package com.example.phonebook.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.phonebook.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity class that is entry point of application and holds fragment views.
 *
 * @constructor Create instance of [MainActivity]
 */

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
