package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.FavoriteProductListViewModelFactoryModule
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.interactor.FavoriteProductsStorage
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.screen.main.adapter.FavoriteTabElementAdapter
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.injectViewModel
import com.falin.valentin.foodapp.viewmodel.FavoriteProductListViewModel
import com.falin.valentin.foodapp.viewmodel.FavoriteProductListViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_tab.*
import javax.inject.Inject

/**
 * [Fragment] subclass to work with FavoriteTabFragment and showing it.
 *
 */
class FavoriteTabFragment : BaseFragment() {
    override val owner: Logger.Owner
        get() = Logger.Owner.FAVORITE_TAB_FRAGMENT

    @Inject
    lateinit var favoriteProductsStorage: FavoriteProductsStorage
    @Inject
    lateinit var viewModelFactory: FavoriteProductListViewModelFactory
    private lateinit var productListViewModel: FavoriteProductListViewModel

    private lateinit var rv: RecyclerView
    private lateinit var favoriteTabRecyclerAdapter: FavoriteTabElementAdapter
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
        return inflater.inflate(R.layout.fragment_favorite_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productListViewModel = injectViewModel(viewModelFactory)
        initViews()
        productListViewModel.products.observe(this, Observer<List<Product>> {
            favoriteTabRecyclerAdapter.updateList(it)
        })
    }

    private fun initViews() {
        favoriteTabRecyclerAdapter = FavoriteTabElementAdapter(favoriteProductsStorage)
        lm = LinearLayoutManager(context)
        rv = fragment_favorite_recycler.apply {
            layoutManager = lm
            adapter = favoriteTabRecyclerAdapter
        }
    }

    private fun initDagger() {
        RebrainApp.DAGGER.initFavoriteTabComponent(
            FavoriteProductListViewModelFactoryModule()
        ).inject(this)
    }

    companion object {
        fun newInstance(): FavoriteTabFragment {
            return FavoriteTabFragment()
        }
    }
}