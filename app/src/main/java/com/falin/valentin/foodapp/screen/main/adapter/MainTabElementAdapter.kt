package com.falin.valentin.foodapp.screen.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.screen.main.carousel.adapter.CarouselStatePageAdapter
import com.google.android.material.tabs.TabLayout


/**
 * Simple [RecyclerView.Adapter] subclass.
 * Have inner class [MainTabProductViewHolder]. Subclass of [RecyclerView.ViewHolder]
 * for display recycler elements.
 *
 */
class MainTabElementAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val CAROUSEL_ID = 0
    var displayMode = LayoutManagerDisplayMode.GRID
    var productList = mutableListOf<Any>()
        private set
    lateinit var carouselPageAdapter: CarouselStatePageAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MainTabAdapterItem.CAROUSEL.ordinal) {
            return MainTabCarouselViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.carousel_element, parent, false)
            )
        }
        val id = when (displayMode) {
            LayoutManagerDisplayMode.LINEAR -> R.layout.card_main_linear_element
            LayoutManagerDisplayMode.GRID -> R.layout.card_main_grid_element
        }
        return MainTabProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(id, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            CAROUSEL_ID -> MainTabAdapterItem.CAROUSEL.ordinal
            else -> MainTabAdapterItem.PRODUCT.ordinal
        }
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MainTabAdapterItem.CAROUSEL.ordinal -> {
                (holder as MainTabCarouselViewHolder).viewPager.adapter = carouselPageAdapter
                holder.tabLayout.setupWithViewPager(holder.viewPager, true)
            }
            else -> (holder as MainTabProductViewHolder).bind(productList[position] as Product)
        }
    }

    fun setProductList(
        list: List<Product>,
        pageAdapter: CarouselStatePageAdapter
    ) {
        productList.clear()
        productList.add(list[CAROUSEL_ID])
        productList.addAll(list)
        carouselPageAdapter = pageAdapter
        notifyDataSetChanged()
    }

    inner class MainTabProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mainElementText: TextView = itemView.findViewById(R.id.card_main_element_text)
        private val mainElementPrice: TextView = itemView.findViewById(R.id.card_main_element_price)
        private val mainElementImage: ImageView = itemView.findViewById(R.id.card_main_element_image)

        fun bind(product: Product) {
            mainElementText.text = product.name
            mainElementPrice.text = "${product.price}"
            Glide.with(mainElementImage.context).load(product.imageUrl).into(mainElementImage)
        }
    }

    inner class MainTabCarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewPager: ViewPager = itemView.findViewById(R.id.carousel_element_tab_pager)
        val tabLayout: TabLayout = itemView.findViewById(R.id.carousel_element_tab_layout)
    }

    /**
     * Enum class for work with enum values for [MainTabElementAdapter]. Used to display the adapter view mode.
     *
     */
    enum class LayoutManagerDisplayMode {
        LINEAR, GRID
    }

    /**
     * Enum class for work with enum values for [MainTabElementAdapter]. Used to select the type of adapter element.
     *
     */
    enum class MainTabAdapterItem {
        CAROUSEL, PRODUCT
    }
}
