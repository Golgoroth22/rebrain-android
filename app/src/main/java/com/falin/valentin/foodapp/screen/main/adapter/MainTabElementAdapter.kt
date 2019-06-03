package com.falin.valentin.foodapp.screen.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.domain.Product

/**
 * Simple [RecyclerView.Adapter] subclass.
 * Have inner class [MainTabElementViewHolder]. It is a subclass of [RecyclerView.ViewHolder]
 * for display recycler elements.
 *
 */
class MainTabElementAdapter : RecyclerView.Adapter<MainTabElementAdapter.MainTabElementViewHolder>() {

    var productList = emptyList<Product>()
        private set


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTabElementViewHolder {
        return MainTabElementViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_main_element, parent, false)
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
        fun bind(product: Product) {
            mainElementText.text = product.name
            mainElementPrice.text = "${product.price}"
        }
    }
}