package com.alyjak.reposearch.events

import com.alyjak.reposearch.network.enums.Order
import com.alyjak.reposearch.network.enums.SortingStrategy

class MakeSearchEvent(
    val query: String,
    val sortingStrategy: SortingStrategy? = null,
    val order: Order? = null
)