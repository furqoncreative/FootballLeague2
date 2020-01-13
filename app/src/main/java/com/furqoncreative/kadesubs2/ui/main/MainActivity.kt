package com.furqoncreative.kadesubs2.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import com.furqoncreative.kadesubs2.R
import com.furqoncreative.kadesubs2.ui.league.LeagueFragment
import com.furqoncreative.kadesubs2.ui.match.MatchFragment
import com.furqoncreative.kadesubs2.ui.search.ResultMatchActivity
import com.pandora.bottomnavigator.BottomNavigator
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var navigator: BottomNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = BottomNavigator.onCreate(
            fragmentContainer = R.id.fragment_container,
            bottomNavigationView = findViewById(R.id.bottomnav_view),
            rootFragmentsFactory = mapOf(
                R.id.tab1 to { LeagueFragment() },
                R.id.tab2 to { MatchFragment() }
            ),
            defaultTab = R.id.tab1,
            activity = this
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val searchView =
                    MenuItemCompat.getActionView(item) as SearchView
                searchView.setOnQueryTextListener(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (!navigator.pop()) {
            super.onBackPressed()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        startActivity<ResultMatchActivity>(
            ResultMatchActivity.QUERY to query
        )
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
