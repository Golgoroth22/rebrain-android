package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.screen.main.adapter.MainTabElementAdapter
import com.falin.valentin.foodapp.screen.main.carousel.adapter.CarouselStatePageAdapter
import com.falin.valentin.foodapp.utils.Generator
import com.falin.valentin.foodapp.utils.Logger
import kotlinx.android.synthetic.main.fragment_main_tab.view.*

/**
 * [Fragment] subclass to work with MainTabFragment and showing it.
 *
 */
class MainTabFragment : BaseFragment() {
    companion object {
        fun newInstance(): MainTabFragment {
            return MainTabFragment()
        }
    }

    private lateinit var rv: RecyclerView
    private lateinit var mainTabPageAdapter: CarouselStatePageAdapter
    private lateinit var mainTabRecyclerAdapter: MainTabElementAdapter
    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var picList: List<Int>

    private val logger = Logger(lifecycle, Logger.Owner.MAIN_TAB_FRAGMENT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        picList = listOf(
            R.drawable.food_1,
            R.drawable.food_2,
            R.drawable.food_3,
            R.drawable.food_4,
            R.drawable.food_5,
            R.drawable.food_6,
            R.drawable.food_7,
            R.drawable.food_8,
            R.drawable.food_9,
            R.drawable.food_10
        )
        mainTabPageAdapter = CarouselStatePageAdapter(childFragmentManager, picList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main_tab, container, false)
        initRv(rootView)
        initListeners(rootView)
        return rootView
    }

    private fun initListeners(rootView: View) {
        rootView.fragment_main_tab_swipe_refresh.setOnRefreshListener {
            mainTabRecyclerAdapter.setProductList(
                Generator().getProducts() as List<Any>,
                picList
            )
            rootView.fragment_main_tab_swipe_refresh.isRefreshing = false
        }
    }

    private fun initRv(rootView: View) {
        rv = rootView.fragment_main_tab_recycler
        mainTabRecyclerAdapter = MainTabElementAdapter(context!!)
        switchRecyclerViewDisplayMode()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainTabRecyclerAdapter.setProductList(
            Generator().getProducts() as List<Any>,
            picList
        )
    }

    fun switchRecyclerViewDisplayMode() {
        when (mainTabRecyclerAdapter.displayMode) {
            MainTabElementAdapter.LayoutManagerDisplayMode.GRID -> {
                lm = LinearLayoutManager(context)
                mainTabRecyclerAdapter.displayMode =
                    MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR
            }
            MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR -> {
                lm = GridLayoutManager(context, 2)
                (lm as GridLayoutManager).spanSizeLookup =
                    object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return when (position) {
                                MainTabElementAdapter.MainTabAdapterItem.CAROUSEL.ordinal -> 2
                                else -> 1
                            }
                        }
                    }
                mainTabRecyclerAdapter.displayMode =
                    MainTabElementAdapter.LayoutManagerDisplayMode.GRID
            }
        }
        rv.apply {
            layoutManager = lm
            adapter = mainTabRecyclerAdapter
        }
        mainTabRecyclerAdapter.notifyDataSetChanged()
    }

    fun getLayoutManagerDisplayMode() = mainTabRecyclerAdapter.displayMode
}