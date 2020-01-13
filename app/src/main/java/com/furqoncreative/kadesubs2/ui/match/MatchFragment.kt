package com.furqoncreative.kadesubs2.ui.match


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.furqoncreative.kadesubs2.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.wrapContent

/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment(), AnkoComponent<Context> {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(AnkoContext.Companion.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        coordinatorLayout {
            lparams(matchParent, matchParent)

            appBarLayout {
                lparams(matchParent, wrapContent)

                tabLayout = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    lparams(matchParent, wrapContent)
                    {
                        tabGravity = TabLayout.GRAVITY_FILL
                        tabMode = TabLayout.MODE_FIXED
                    }
                }
            }
            viewPager = viewPager {
                id = R.id.viewpager
            }.lparams(matchParent, matchParent)
            (viewPager.layoutParams as CoordinatorLayout.LayoutParams).behavior =
                AppBarLayout.ScrollingViewBehavior()


            val fragmentAdapter = MyPagerAdapter(childFragmentManager)
            viewPager.adapter = fragmentAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    PrevMatchFragment()
                }
                else -> {
                    return NextMatchFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Previous Match"
                else -> {
                    return "Next Match"
                }
            }
        }
    }


}
