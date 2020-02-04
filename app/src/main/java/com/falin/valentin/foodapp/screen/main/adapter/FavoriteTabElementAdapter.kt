package com.falin.valentin.foodapp.screen.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.interactor.FavoriteProductsStorage

/**
 * Simple [RecyclerView.Adapter] subclass.
 * Have inner class [FavoriteTabElementViewHolder]. Subclass of [RecyclerView.ViewHolder] uses
 * for display recycler elements.
 *
 */
class FavoriteTabElementAdapter :
    RecyclerView.Adapter<FavoriteTabElementAdapter.FavoriteTabElementViewHolder>() {
    private var adapterList = emptyList<Product>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteTabElementViewHolder {
        return FavoriteTabElementViewHolder(parent, R.layout.card_favorite_linear_element)
    }

    override fun getItemCount() = adapterList.size

    override fun onBindViewHolder(holder: FavoriteTabElementViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    /**
     * This method can be called for update [adapterList].
     *
     */
    fun updateList(list: List<Product>) {
        adapterList = list
        notifyDataSetChanged()
    }

    inner class FavoriteTabElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        constructor(parent: ViewGroup, layoutId: Int) : this(
            LayoutInflater.from(parent.context)
                .inflate(layoutId, parent, false)
        )

        private val titleText: TextView = itemView.findViewById(R.id.card_favorite_element_text)
        private val priceText: TextView = itemView.findViewById(R.id.card_favorite_element_price)
        private val favoriteImage: ImageView =
            itemView.findViewById(R.id.card_favorite_element_favorite_image)
        private val image: ImageView =
            itemView.findViewById(R.id.card_favorite_element_image)

        fun bind(product: Product) {
            titleText.text = product.name
            priceText.text = "${product.price}"
            Glide.with(image.context).load(product.imageUrl).into(image)
            favoriteImage.setOnClickListener {
                updateList(adapterList.filter {
                    product != it
                })
                RebrainApp.DAGGER.favoriteProductsStorage().removeProduct(product)
            }
        }
    }
}