package com.alyjak.reposearch.network.enums

enum class SortingStrategy(val sortType: String) {
    STARS("stars"),
    FORKS("forks"),
    HELP_WANTED_ISSUES("help-wanted-issues"),
    UPDATED("updated")
}