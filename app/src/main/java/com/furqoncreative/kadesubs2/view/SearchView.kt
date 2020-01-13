package com.furqoncreative.kadesubs2.view

import com.furqoncreative.kadesubs2.model.Event

interface SearchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Event>)
    fun showEmptyData()
}