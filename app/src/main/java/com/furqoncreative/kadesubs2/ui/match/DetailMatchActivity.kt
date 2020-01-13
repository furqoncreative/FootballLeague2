package com.furqoncreative.kadesubs2.ui.match

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.furqoncreative.kadesubs2.R
import com.furqoncreative.kadesubs2.api.ApiRepository
import com.furqoncreative.kadesubs2.model.Match
import com.furqoncreative.kadesubs2.model.MatchResponse
import com.furqoncreative.kadesubs2.model.Team
import com.furqoncreative.kadesubs2.model.TeamResponse
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchActivity : AppCompatActivity() {
    companion object {
        const val ID = "id"
    }

    private var id: String = ""


    private lateinit var cardTop: CardView
    private lateinit var cardBottom: CardView

    private lateinit var eventLocation: TextView
    private lateinit var eventTime: TextView
    private lateinit var eventDate: TextView
    private lateinit var eventHome: TextView
    private lateinit var eventAway: TextView
    private lateinit var scoreHome: TextView
    private lateinit var scoreAway: TextView
    private lateinit var versus: TextView
    private lateinit var logoAway: ImageView
    private lateinit var logoHome: ImageView
    private lateinit var progressBar: ProgressBar


    private lateinit var teamHome: TextView
    private lateinit var teamAway: TextView
    private lateinit var strHomeGoalDetails: TextView
    private lateinit var strHomeRedCards: TextView
    private lateinit var strHomeYellowCards: TextView
    private lateinit var strHomeLineupGoalkeeper: TextView
    private lateinit var strHomeLineupDefense: TextView
    private lateinit var strHomeLineupMidfield: TextView
    private lateinit var strHomeLineupForward: TextView
    private lateinit var strHomeLineupSubstitutes: TextView
    private lateinit var strAwayRedCards: TextView
    private lateinit var strAwayYellowCards: TextView
    private lateinit var strAwayGoalDetails: TextView
    private lateinit var strAwayLineupGoalkeeper: TextView
    private lateinit var strAwayLineupDefense: TextView
    private lateinit var strAwayLineupMidfield: TextView
    private lateinit var strAwayLineupForward: TextView
    private lateinit var strAwayLineupSubstitutes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scrollView {
            lparams(width = matchParent, height = matchParent)
            verticalLayout {
                lparams(width = matchParent, height = matchParent)
                padding = dip(14)

                cardTop = cardView {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(12)
                        radius = 12f
                    }
                    visibility = View.GONE

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(10)
                        orientation = LinearLayout.HORIZONTAL
                        weightSum = 3F

                        linearLayout {
                            lparams(width = 0, height = wrapContent, weight = 1F)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER


                            eventHome = textView {
                                textSize = 14f
                                gravity = Gravity.CENTER

                            }.lparams {
                                topMargin = dip(16)
                            }

                            logoHome = imageView {
                                id = R.id.logo_home
                            }.lparams {
                                height = dip(50)
                                width = dip(50)
                            }
                        }

                        linearLayout {
                            lparams(width = 0, height = matchParent, weight = 1F)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER

                            scoreHome = textView {
                                textSize = 20f
                            }

                            versus = textView {
                                textSize = 18f
                                gravity = Gravity.CENTER
                            }

                            scoreAway = textView {
                                textSize = 20f
                            }
                        }


                        linearLayout {
                            lparams(width = 0, height = wrapContent, weight = 1F)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER

                            eventAway = textView {
                                textSize = 14f
                                gravity = Gravity.CENTER
                            }.lparams {
                                topMargin = dip(16)
                            }

                            logoAway = imageView {
                            }.lparams {
                                height = dip(50)
                                width = dip(50)
                            }

                        }
                    }

                }

                cardBottom = cardView {
                    lparams(width = matchParent, height = matchParent) {
                        margin = dip(12)
                        radius = 12f
                    }
                    visibility = View.GONE

                    linearLayout {
                        lparams(width = matchParent, height = matchParent)
                        orientation = LinearLayout.VERTICAL


                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER

                            eventLocation = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                            }.lparams {
                                topMargin = dip(8)
                                bottomMargin = dip(8)
                            }

                            eventDate = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                topMargin = dip(8)
                            }

                            eventTime = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                            }

                        }

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            weightSum = 2f

                            teamHome = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            teamAway = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                        }

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            weightSum = 3f

                            strHomeGoalDetails = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                                text = "Goals Details"

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            strAwayGoalDetails = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                        }


                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            weightSum = 3f

                            strHomeRedCards = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                                text = "Red Cards"

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            strAwayRedCards = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                        }

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            weightSum = 3f

                            strHomeYellowCards = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                                text = "Yellow Cards"

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            strAwayYellowCards = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                        }



                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            weightSum = 3f

                            strHomeLineupGoalkeeper = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                                text = "Lineup Goalkeeper"

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            strAwayLineupGoalkeeper = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                        }

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            weightSum = 3f

                            strHomeLineupDefense = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                                text = "Lineup Defense"

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            strAwayLineupDefense = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                        }

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            weightSum = 3f

                            strHomeLineupMidfield = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                                text = "Lineup Midfield"

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            strAwayLineupMidfield = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                        }

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            weightSum = 3f

                            strHomeLineupForward = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                                text = "Lineup Foward"

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            strAwayLineupForward = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                        }

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            weightSum = 3f

                            strHomeLineupSubstitutes = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                                text = "Lineup Subtitutes"

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                            strAwayLineupSubstitutes = textView {
                                textSize = 12f
                                gravity = Gravity.CENTER
                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(8)
                                weight = 1f
                                width = 0
                            }

                        }

                    }


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
        ApiRepository().services.getDetailMatch(id).enqueue(object :
            Callback<MatchResponse> {
            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                if (response.isSuccessful) {
                    Log.d("ID", "" + id)
                    val match: Match? = response.body()?.events?.get(0)

                    if (match != null) {
                        eventHome.text = match.strHomeTeam
                        teamHome.text = match.strHomeTeam
                        eventAway.text = match.strAwayTeam
                        teamAway.text = match.strAwayTeam
                        eventTime.text = match.strTime
                        eventDate.text = match.strDate
                        eventLocation.text = match.strLeague
                        versus.text = " vs "
                        if (match.intHomeScore != null) {
                            scoreHome.text = match.intHomeScore.toString()
                            scoreAway.text = match.intAwayScore.toString()

                        } else {
                            scoreHome.text = "-"
                            scoreAway.text = "-"

                        }


                        strHomeGoalDetails.text = match.strHomeGoalDetails
                        strAwayGoalDetails.text = match.strAwayGoalDetails

                        strAwayRedCards.text = match.strAwayRedCards
                        strHomeRedCards.text = match.strHomeRedCards

                        strHomeYellowCards.text = match.strHomeYellowCards
                        strAwayYellowCards.text = match.strAwayYellowCards

                        strHomeLineupDefense.text = match.strHomeLineupDefense
                        strAwayLineupDefense.text = match.strAwayLineupDefense

                        strHomeLineupGoalkeeper.text = match.strHomeLineupGoalkeeper
                        strAwayLineupGoalkeeper.text = match.strAwayLineupGoalkeeper

                        strHomeLineupMidfield.text = match.strHomeLineupMidfield
                        strAwayLineupMidfield.text = match.strAwayLineupMidfield

                        strHomeLineupForward.text = match.strHomeLineupForward
                        strAwayLineupForward.text = match.strAwayLineupForward

                        strHomeLineupSubstitutes.text = match.strHomeLineupSubstitutes
                        strAwayLineupSubstitutes.text = match.strAwayLineupSubstitutes



                        getLogoHome(match.idHomeTeam)
                        getLogoAway(match.idAwayTeam)

                    }

                }
                hideLoading()
                cardTop.visibility = View.VISIBLE
                cardBottom.visibility = View.VISIBLE

            }

            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                Log.e("Detail Response", "" + t)

            }
        })
    }

    fun getLogoHome(id: String) {
        ApiRepository().services.getDetailTeam(id).enqueue(object :
            Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                //Tulis code jika response sukses
                if (response.isSuccessful) {
                    Log.d("ID", "" + id)
                    val team: Team? = response.body()?.teams?.get(0)
                    Glide.with(applicationContext).load(team?.strTeamLogo).into(logoHome)
                }

            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Log.e("Detail Response", "" + t)

            }
        })
    }

    fun getLogoAway(id: String) {
        ApiRepository().services.getDetailTeam(id).enqueue(object :
            Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                //Tulis code jika response sukses
                if (response.isSuccessful) {
                    Log.d("ID", "" + id)
                    val team: Team? = response.body()?.teams?.get(0)
                    Glide.with(applicationContext).load(team?.strTeamLogo).into(logoAway)
                }

            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Log.e("Detail Response", "" + t)

            }
        })
    }
}