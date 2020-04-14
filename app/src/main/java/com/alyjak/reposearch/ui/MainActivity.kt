package com.alyjak.reposearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alyjak.reposearch.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, ResultOfSearchingFragment.newInstance())
            .commit()
    }

}
