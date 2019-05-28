package com.falin.valentin.foodapp.screen.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.domain.Product

class MainTabElementAdapter(
    context: Context?
) : RecyclerView.Adapter<MainTabElementAdapter.MainTabElementViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var productList = emptyList<Product>()
        private set

    inner class MainTabElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mainElementText: TextView = itemView.findViewById(R.id.card_main_element_text)
        fun bind(product: Product) {
            mainElementText.text = product.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTabElementViewHolder {
        return MainTabElementViewHolder(inflater.inflate(R.layout.card_main_element, parent, false))
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: MainTabElementViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    fun setProductList(list: List<Any?>) {
        productList = list as List<Product>
        notifyDataSetChanged()
    }
}