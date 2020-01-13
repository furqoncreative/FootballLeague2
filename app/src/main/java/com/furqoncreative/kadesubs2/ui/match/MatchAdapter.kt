package com.furqoncreative.kadesubs2.ui.match

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furqoncreative.kadesubs2.R
import com.furqoncreative.kadesubs2.api.ApiRepository
import com.furqoncreative.kadesubs2.model.Match
import com.furqoncreative.kadesubs2.model.Team
import com.furqoncreative.kadesubs2.model.TeamResponse
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MatchAdapter(
    context: Context?,
    private val items: List<Match>,
    private val listener: (Match) -> Unit
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            EventItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(items[position], listener)

    }

    override fun getItemCount(): Int = items.size

    class EventItemUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {

                cardView {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(5)
                        radius = 8f
                    }

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(10)
                        orientation = LinearLayout.HORIZONTAL
                        weightSum = 3F

                        linearLayout {
                            lparams(width = 0, height = wrapContent, weight = 1F)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER


                            textView {
                                id = R.id.event_home
                                textSize = 14f
                                gravity = Gravity.CENTER

                            }.lparams {
                                topMargin = dip(16)
                            }

                            imageView {
                                id = R.id.logo_home
                            }.lparams {
                                height = dip(50)
                                width = dip(50)
                            }

                            textView {
                                id = R.id.event_home
                                textSize = 14f
                                gravity = Gravity.CENTER

                            }.lparams {
                                topMargin = dip(16)
                            }
                        }

                        linearLayout {
                            lparams(width = 0, height = wrapContent, weight = 1F)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER

                            textView {
                                id = R.id.event_date
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                topMargin = dip(16)
                            }

                            textView {
                                id = R.id.event_time
                                textSize = 12f
                                gravity = Gravity.CENTER

                            }.lparams {
                                marginEnd = dip(4)
                                marginStart = dip(4)
                                bottomMargin = dip(16)
                            }

                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER

                                textView {
                                    id = R.id.score_home
                                    textSize = 20f
                                }

                                textView {
                                    textSize = 18f
                                    text = " vs "
                                }

                                textView {
                                    id = R.id.score_away
                                    textSize = 20f
                                }


                            }

                            textView {
                                id = R.id.event_location
                                textSize = 12f
                                gravity = Gravity.CENTER
                            }.lparams {
                                topMargin = dip(16)
                                bottomMargin = dip(16)
                            }

                        }


                        linearLayout {
                            lparams(width = 0, height = wrapContent, weight = 1F)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER

                            textView {
                                id = R.id.event_away
                                textSize = 14f
                                gravity = Gravity.CENTER
                            }.lparams {
                                topMargin = dip(16)
                            }

                            imageView {
                                id = R.id.logo_away
                            }.lparams {
                                height = dip(50)
                                width = dip(50)
                            }


                        }


                    }

                }


            }
        }
    }


    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val eventLocation: TextView = view.find(R.id.event_location)
        private val eventTime: TextView = view.find(R.id.event_time)
        private val eventDate: TextView = view.find(R.id.event_date)
        private val eventHome: TextView = view.find(R.id.event_home)
        private val eventAway: TextView = view.find(R.id.event_away)
        private val scoreHome: TextView = view.find(R.id.score_home)
        private val scoreAway: TextView = view.find(R.id.score_away)
        private val logoAway: ImageView = view.find(R.id.logo_away)
        private val logoHome: ImageView = view.find(R.id.logo_home)

        fun bindItem(items: Match, listener: (Match) -> Unit) {

            eventHome.text = items.strHomeTeam
            eventAway.text = items.strAwayTeam
            eventTime.text = items.strTime
            eventDate.text = items.strDate
            eventLocation.text = items.strLeague
            if (items.intHomeScore != null) {
                scoreHome.text = items.intHomeScore.toString()
                scoreAway.text = items.intAwayScore.toString()
            } else {
                scoreHome.text = "-"
                scoreAway.text = "-"
            }
            getLogoHome(items.idHomeTeam)
            getLogoAway(items.idAwayTeam)



            itemView.setOnClickListener {
                listener(items)
            }
        }

        private fun getLogoHome(id: String) {
            ApiRepository().services.getDetailTeam(id).enqueue(object :
                Callback<TeamResponse> {
                override fun onResponse(
                    call: Call<TeamResponse>,
                    response: Response<TeamResponse>
                ) {
                    //Tulis code jika response sukses
                    if (response.isSuccessful) {
                        Log.d("ID", "" + id)
                        val team: Team? = response.body()?.teams?.get(0)
                        Glide.with(itemView.context).load(team?.strTeamLogo).into(logoHome)
                    }

                }

                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    Log.e("Detail Response", "" + t)

                }
            })
        }

        private fun getLogoAway(id: String) {
            ApiRepository().services.getDetailTeam(id).enqueue(object :
                Callback<TeamResponse> {
                override fun onResponse(
                    call: Call<TeamResponse>,
                    response: Response<TeamResponse>
                ) {
                    //Tulis code jika response sukses
                    if (response.isSuccessful) {
                        Log.d("ID", "" + id)
                        val team: Team? = response.body()?.teams?.get(0)
                        Glide.with(itemView.context).load(team?.strTeamLogo).into(logoAway)
                    }

                }

                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    Log.e("Detail Response", "" + t)

                }
            })
        }
    }

}

