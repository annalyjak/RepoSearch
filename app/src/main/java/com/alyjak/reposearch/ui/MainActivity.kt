package com.alyjak.reposearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alyjak.reposearch.R
import com.alyjak.reposearch.ui.result.ResultOfSearchingFragment
import com.alyjak.reposearch.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.searching_container, SearchFragment.newInstance())
            .replace(R.id.result_container, ResultOfSearchingFragment.newInstance())
            .commit()
    }

}
