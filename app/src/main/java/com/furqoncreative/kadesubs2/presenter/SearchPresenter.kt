package com.furqoncreative.kadesubs2.presenter

import com.furqoncreative.kadesubs2.api.ApiRepository
import com.furqoncreative.kadesubs2.model.SearchResponse
import com.furqoncreative.kadesubs2.view.SearchView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(private val apiRepository: ApiRepository, private val view: SearchView) {

    fun getResultMatch(query: String) {
        view.showLoading()
        apiRepository.services.getResultSearch(query).enqueue(object :
            Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.code() == 200) {
                    if (response.body()?.event != null) {
                        view.showMatchList(response.body()!!.event)
                        view.hideLoading()
                    } else {
                        view.showEmptyData()
                        view.hideLoading()
                    }

                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {


            }
        })
    }
}