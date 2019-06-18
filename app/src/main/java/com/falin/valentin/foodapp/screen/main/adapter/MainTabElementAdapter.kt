package com.falin.valentin.foodapp.screen.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.domain.Product

/**
 * Simple [RecyclerView.Adapter] subclass.
 * Have inner class [MainTabElementViewHolder]. Subclass of [RecyclerView.ViewHolder]
 * for display recycler elements.
 *
 */
class MainTabElementAdapter : RecyclerView.Adapter<MainTabElementAdapter.MainTabElementViewHolder>() {
    var displayMode = LayoutManagerDisplayMode.GRID
    var productList = emptyList<Product>()
        private set


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTabElementViewHolder {
        val id = when (displayMode) {
            LayoutManagerDisplayMode.LINEAR -> R.layout.card_main_linear_element
            LayoutManagerDisplayMode.GRID -> R.layout.card_main_grid_element
        }
        return MainTabElementViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(id, parent, false)
        )
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: MainTabElementViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    fun setProductList(list: List<Product>) {
        productList = list
        notifyDataSetChanged()
    }

    inner class MainTabElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mainElementText: TextView = itemView.findViewById(R.id.card_main_element_text)
        private val mainElementPrice: TextView = itemView.findViewById(R.id.card_main_element_price)
        private val mainElementImage: ImageView = itemView.findViewById(R.id.card_main_element_image)

        fun bind(product: Product) {
            mainElementText.text = product.name
            mainElementPrice.text = "${product.price}"
            Glide.with(mainElementImage.context).load(product.imageUrl).into(mainElementImage);
        }
    }

    /**
     * Enum class for work with enum values for [MainTabElementAdapter].
     *
     */
    enum class LayoutManagerDisplayMode {
        LINEAR, GRID
    }
}