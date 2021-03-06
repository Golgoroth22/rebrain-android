package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.ProductListViewModelFactoryModule
import com.falin.valentin.foodapp.di.module.ProductModeStorageModule
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.screen.main.adapter.MainTabElementAdapter
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.injectViewModel
import com.falin.valentin.foodapp.viewmodel.ProductListViewModel
import com.falin.valentin.foodapp.viewmodel.factories.ProductListViewModelFactory
import kotlinx.android.synthetic.main.fragment_main_tab.view.*
import javax.inject.Inject

/**
 * [Fragment] subclass for work with MainTabFragment and showing it.
 *
 */
class MainTabFragment : BaseFragment() {
    override val owner: Logger.Owner
        get() = Logger.Owner.MAIN_TAB_FRAGMENT

    @Inject
    lateinit var viewModelFactory: ProductListViewModelFactory
    private lateinit var productListViewModel: ProductListViewModel

    private lateinit var rv: RecyclerView
    private lateinit var mainTabRecyclerAdapter: MainTabElementAdapter
    private lateinit var lm: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main_tab, container, false)
        initListeners(rootView)
        return rootView
    }

    private fun initDagger() {
        RebrainApp.DAGGER.initMainTabComponent(
            ProductListViewModelFactoryModule(),
            ProductModeStorageModule()
        ).inject(this)
    }

    private fun initListeners(rootView: View) {
        rootView.fragment_main_tab_swipe_refresh.setOnRefreshListener {
            productListViewModel.getProducts()
            rootView.fragment_main_tab_swipe_refresh.isRefreshing = false
        }
    }

    private fun initRv(rootView: View) {
        rv = rootView.fragment_main_tab_recycler
        mainTabRecyclerAdapter =
            MainTabElementAdapter(
                context!!,
                productListViewModel.getProductsDisplayMode()
            ) { product -> productListViewModel.addProductToFavorites(product) }
        selectLayoutManager()
        rv.apply {
            layoutManager = lm
            adapter = mainTabRecyclerAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productListViewModel = injectViewModel(viewModelFactory)
        initRv(view)
        productListViewModel.picturesLiveData.observe(this, Observer { pList ->
            productListViewModel.picturesList = pList
            mainTabRecyclerAdapter.setProductList(
                productListViewModel.productsList,
                pList
            )
        })
        productListViewModel.productsLiveData.observe(this, Observer { productList ->
            productListViewModel.productsList = productList
            mainTabRecyclerAdapter.setProductList(
                productList,
                productListViewModel.picturesList
            )
        })
    }

    /**
     * This method can be called for switch [mainTabRecyclerAdapter] display mode.
     *
     */
    fun switchRecyclerViewDisplayMode() {
        selectLayoutManager()
        productListViewModel.switchDisplayMode()
        rv.apply {
            layoutManager = lm
            adapter = mainTabRecyclerAdapter
        }
        mainTabRecyclerAdapter.notifyDataSetChanged()
    }

    private fun selectLayoutManager() {
        when (mainTabRecyclerAdapter.displayMode) {
            MainTabElementAdapter.LayoutManagerDisplayMode.GRID.ordinal -> {
                lm = LinearLayoutManager(context)
                mainTabRecyclerAdapter.displayMode =
                    MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR.ordinal
            }
            MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR.ordinal -> {
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
                    MainTabElementAdapter.LayoutManagerDisplayMode.GRID.ordinal
            }
        }
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