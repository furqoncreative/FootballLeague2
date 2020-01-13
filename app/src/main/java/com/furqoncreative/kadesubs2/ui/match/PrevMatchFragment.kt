package com.furqoncreative.kadesubs2.ui.match


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.furqoncreative.kadesubs2.R
import com.furqoncreative.kadesubs2.api.ApiRepository
import com.furqoncreative.kadesubs2.model.Match
import com.furqoncreative.kadesubs2.presenter.MatchPresenter
import com.furqoncreative.kadesubs2.view.MatchView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * A simple [Fragment] subclass.
 */
class PrevMatchFragment : Fragment(), AnkoComponent<Context>, MatchView {

    private var items: MutableList<Match> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var spinner: Spinner
    private lateinit var listItem: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var spinnerId: Int = 0
    private lateinit var leagueId: Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(AnkoContext.Companion.create(requireContext()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinnerItems = resources.getStringArray(R.array.league_name)
        leagueId = resources.getStringArray(R.array.league_id)
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItems
        )
        spinner.adapter = spinnerAdapter

        adapter = MatchAdapter(context, items) {
            context?.startActivity<DetailMatchActivity>(DetailMatchActivity.ID to "${it.idEvent}")
        }
        listItem.adapter = adapter

        val request = ApiRepository()
        presenter = MatchPresenter(request, this)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {

                spinnerId = spinner.selectedItemPosition

                presenter.getPrevMatch(leagueId[spinnerId])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getPrevMatch(leagueId[spinnerId])

        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(8)
            leftPadding = dip(8)
            rightPadding = dip(8)

            spinner = spinner {
                id = R.id.spinner
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listItem = recyclerView {
                        id = R.id.list_match
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        listItem.visibility = View.INVISIBLE

    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
        listItem.visibility = View.VISIBLE

    }

    override fun showMatchList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
