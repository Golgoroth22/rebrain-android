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
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.screen.main.adapter.MainTabElementAdapter
import com.falin.valentin.foodapp.screen.main.carousel.adapter.CarouselStatePageAdapter
import com.falin.valentin.foodapp.utils.Generator
import kotlinx.android.synthetic.main.fragment_main_tab.*
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
    private lateinit var pageAdapter: CarouselStatePageAdapter
    private lateinit var mainTabElementAdapter: MainTabElementAdapter
    private lateinit var lm: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val picList = listOf(
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
        pageAdapter = CarouselStatePageAdapter(childFragmentManager, picList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main_tab, container, false)
        initRv(rootView)
        initListeners(rootView)
        return rootView
    }

    private fun initListeners(rootView: View) {
        rootView.fragment_main_tab_swipe_refresh.setOnRefreshListener {
            mainTabElementAdapter.setProductList(Generator().getProducts() as List<Product>)
            rootView.fragment_main_tab_swipe_refresh.isRefreshing = false
        }
    }

    private fun initRv(rootView: View) {
        rv = rootView.fragment_main_tab_recycler
        mainTabElementAdapter = MainTabElementAdapter()
        setRecyclerViewDisplayMode()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_fragment_tab_pager.adapter = pageAdapter
        mainTabElementAdapter.setProductList(Generator().getProducts() as List<Product>)
    }

    fun setRecyclerViewDisplayMode() {
        when (mainTabElementAdapter.displayMode) {
            MainTabElementAdapter.LayoutManagerDisplayMode.GRID -> {
                lm = LinearLayoutManager(context)
                mainTabElementAdapter.displayMode = MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR
            }
            MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR -> {
                lm = GridLayoutManager(context, 2)
                mainTabElementAdapter.displayMode = MainTabElementAdapter.LayoutManagerDisplayMode.GRID
            }
        }
        rv.apply {
            layoutManager = lm
            adapter = mainTabElementAdapter
        }
        mainTabElementAdapter.notifyDataSetChanged()
    }

    fun getLayoutManagerDisplayMode() = mainTabElementAdapter.displayMode
}