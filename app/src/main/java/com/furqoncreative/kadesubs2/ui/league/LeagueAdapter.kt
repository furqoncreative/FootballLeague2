package com.furqoncreative.kadesubs2.ui.league

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furqoncreative.kadesubs2.R
import com.furqoncreative.kadesubs2.model.LeagueItem
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class LeagueAdapter(
    context: Context,
    private val items: List<LeagueItem>,
    private val listener: (LeagueItem) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            LeagueItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(items[position], listener)

    }

    override fun getItemCount(): Int = items.size

    class LeagueItemUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {

                cardView {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(5)
                    }

                    verticalLayout {
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(16)
                        orientation = LinearLayout.HORIZONTAL

                        imageView {
                            id = R.id.league_logo
                        }.lparams {
                            height = dip(50)
                            width = dip(50)
                        }

                        textView {
                            id = R.id.league_name
                            textSize = 14f
                        }.lparams {
                            margin = dip(16)
                        }


                    }

                }


            }
        }
    }

    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val leagueLogo: ImageView = view.find(R.id.league_logo)
        private val leagueName: TextView = view.find(R.id.league_name)

        fun bindItem(items: LeagueItem, listener: (LeagueItem) -> Unit) {
            Glide.with(itemView.context)
                .load(items.logo)
                .into(leagueLogo)

            leagueName.text = items.name

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

}

