package com.furqoncreative.kadesubs2.ui.league

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.furqoncreative.kadesubs2.api.ApiRepository
import com.furqoncreative.kadesubs2.model.League
import com.furqoncreative.kadesubs2.model.LeagueResponse
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID = "id"
    }

    private var id: String = ""

    private lateinit var nameTextView: TextView
    private lateinit var logoImageView: ImageView
    private lateinit var descTextView: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scrollView {
            lparams(width = matchParent, height = matchParent)
            linearLayout {
                lparams(width = matchParent, height = matchParent)
                padding = dip(14)
                orientation = LinearLayout.VERTICAL

                logoImageView = imageView().lparams(width = dip(200), height = wrapContent) {
                    gravity = Gravity.CENTER
                }
                nameTextView = textView().lparams(width = wrapContent) {
                    gravity = Gravity.CENTER
                    topMargin = dip(10)
                }
                descTextView = textView().lparams(width = wrapContent) {
                    topMargin = dip(20)
                    leftMargin = dip(20)
                    rightMargin = dip(20)
                    textAlignment = View.TEXT_ALIGNMENT_INHERIT
                }

                progressBar = progressBar {
                }.lparams {
                    gravity = Gravity.CENTER
                }

            }
        }

        id = intent.getStringExtra(ID)
//
        getData(id)

    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }


    private fun getData(id: String) {
        showLoading()
        ApiRepository().services.getDetailLeague(id).enqueue(object :
            Callback<LeagueResponse> {
            override fun onResponse(
                call: Call<LeagueResponse>,
                response: Response<LeagueResponse>
            ) {
                //Tulis code jika response sukses
                if (response.isSuccessful) {
                    Log.d("ID", "" + id)
                    val league: League? = response.body()?.leagues?.get(0)


                    Glide.with(applicationContext).load(league?.strLogo).into(logoImageView)
                    nameTextView.text = league?.strLeague
                    descTextView.text = league?.strDescriptionEN
                }
                hideLoading()

            }

            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                Log.e("Detail Response", "" + t)

            }
        })
    }
}