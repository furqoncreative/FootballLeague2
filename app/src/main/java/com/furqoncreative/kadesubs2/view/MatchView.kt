package com.furqoncreative.kadesubs2.view

import com.furqoncreative.kadesubs2.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}