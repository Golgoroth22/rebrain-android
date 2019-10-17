package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.repository.ProductsRepository
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.screen.main.adapter.MainTabElementAdapter
import com.falin.valentin.foodapp.screen.main.carousel.adapter.CarouselStatePageAdapter
import com.falin.valentin.foodapp.utils.Generator
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.viewmodel.ProductListViewModel
import com.falin.valentin.foodapp.viewmodel.ProductListViewModelFactory
import kotlinx.android.synthetic.main.fragment_main_tab.view.*

/**
 * [Fragment] subclass for work with MainTabFragment and showing it.
 *
 */
class MainTabFragment : BaseFragment() {
    override val owner: Logger.Owner
        get() = Logger.Owner.MAIN_TAB_FRAGMENT

    private lateinit var productListViewModel: ProductListViewModel

    private lateinit var rv: RecyclerView
    private lateinit var mainTabRecyclerAdapter: MainTabElementAdapter
    private lateinit var lm: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main_tab, container, false)
        initRv(rootView)
        initListeners(rootView)
        return rootView
    }

    private fun initListeners(rootView: View) {
        rootView.fragment_main_tab_swipe_refresh.setOnRefreshListener {
            mainTabRecyclerAdapter.setProductList(
                productListViewModel.getProducts(),
                productListViewModel.getPictures()
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
        productListViewModel = ViewModelProviders.of(
            this, ProductListViewModelFactory(ProductsRepository(Generator()))
        ).get(ProductListViewModel::class.java)
        mainTabRecyclerAdapter.setProductList(
            productListViewModel.getProducts(),
            productListViewModel.getPictures()
        )
    }

    /**
     * This method can be called for switch [mainTabRecyclerAdapter] display mode.
     *
     */
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

    /**
     * This method can be called for get current [mainTabRecyclerAdapter] display mode view.
     *
     */
    fun getLayoutManagerDisplayMode() = mainTabRecyclerAdapter.displayMode

    companion object {
        fun newInstance(): MainTabFragment {
            return MainTabFragment()
        }
    }
}