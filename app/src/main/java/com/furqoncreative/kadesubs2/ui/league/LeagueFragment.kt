package com.furqoncreative.kadesubs2.ui.league


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.furqoncreative.kadesubs2.R
import com.furqoncreative.kadesubs2.model.LeagueItem
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.verticalLayout

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment(), AnkoComponent<Context> {
    private var items: MutableList<LeagueItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(AnkoContext.Companion.create(requireContext()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()

    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        verticalLayout {

            recyclerView {
                lparams(width = matchParent, height = matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = LeagueAdapter(
                    context,
                    items
                ) { itemClicked(it) }
            }
        }
    }

    private fun loadData() {
        val logo = resources.obtainTypedArray(R.array.league_logo)
        val name = resources.getStringArray(R.array.league_name)
        val id = resources.getStringArray(R.array.league_id)

        items.clear()
        for (i in name.indices) {
            items.add(
                LeagueItem(
                    id[i],
                    name[i],
                    logo.getResourceId(i, 0)
                )
            )
        }

        logo.recycle()
    }

    private fun itemClicked(items: LeagueItem) {
        startActivity<DetailActivity>(
            DetailActivity.ID to items.id
        )

    }

}
