package com.falin.valentin.foodapp.screen.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.screen.main.carousel.adapter.CarouselStatePageAdapter
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.toast


/**
 * Simple [RecyclerView.Adapter] subclass.
 * Have inner class [MainTabProductViewHolder]. Subclass of [RecyclerView.ViewHolder]
 * for display recycler elements.
 *
 */
class MainTabElementAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    constructor(context: Context) : this() {
        basketButtonListener = { id: String -> context.toast(id) }
    }

    private var basketButtonListener: ((String) -> Toast)? = null
    var displayMode = LayoutManagerDisplayMode.GRID
    var adapterList = mutableListOf<Any>()
        private set

    lateinit var carouselPageAdapter: CarouselStatePageAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MainTabAdapterItem.CAROUSEL.ordinal) {
            return MainTabCarouselViewHolder(parent, R.layout.carousel_element)
        }
        val id = when (displayMode) {
            LayoutManagerDisplayMode.LINEAR -> R.layout.card_main_linear_element
            LayoutManagerDisplayMode.GRID -> R.layout.card_main_grid_element
        }
        return MainTabProductViewHolder(parent, id)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            CAROUSEL_ID -> MainTabAdapterItem.CAROUSEL.ordinal
            else -> MainTabAdapterItem.PRODUCT.ordinal
        }
    }

    override fun getItemCount() = adapterList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MainTabAdapterItem.CAROUSEL.ordinal -> (holder as MainTabCarouselViewHolder).bind()
            MainTabAdapterItem.PRODUCT.ordinal -> (holder as MainTabProductViewHolder).bind(adapterList[position] as Product)
        }
    }

    fun setProductList(
        list: List<Any>,
        pageAdapter: CarouselStatePageAdapter
    ) {
        adapterList.clear()
        adapterList.add(list[CAROUSEL_ID])
        adapterList.addAll(list)
        carouselPageAdapter = pageAdapter
        notifyDataSetChanged()
    }

    inner class MainTabProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        constructor(parent: ViewGroup, layoutId: Int) : this(
            LayoutInflater.from(parent.context).inflate(
                layoutId,
                parent,
                false
            )
        )


        private val mainElementText: TextView = itemView.findViewById(R.id.card_main_element_text)
        private val mainElementPrice: TextView = itemView.findViewById(R.id.card_main_element_price)
        private val mainElementImage: ImageView = itemView.findViewById(R.id.card_main_element_image)
        private val mainElementBasketButton: ImageButton = itemView.findViewById(R.id.card_main_element_basket_button)

        fun bind(product: Product) {
            mainElementText.text = product.name
            mainElementPrice.text = "${product.price}"
            Glide.with(mainElementImage.context).load(product.imageUrl).into(mainElementImage)
            mainElementBasketButton.setOnClickListener {
                basketButtonListener?.invoke(product.name)
            }
        }
    }

    inner class MainTabCarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        constructor(parent: ViewGroup, layoutId: Int) : this(
            LayoutInflater.from(parent.context)
                .inflate(layoutId, parent, false)
        )

        private val viewPager: ViewPager = itemView.findViewById(R.id.carousel_element_tab_pager)
        private val tabLayout: TabLayout = itemView.findViewById(R.id.carousel_element_tab_layout)

        fun bind() {
            viewPager.adapter = carouselPageAdapter
            tabLayout.setupWithViewPager(viewPager, true)
        }
    }

    companion object {
        private const val CAROUSEL_ID = 0
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
