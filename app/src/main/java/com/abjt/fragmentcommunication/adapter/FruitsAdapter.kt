package com.abjt.fragmentcommunication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abjt.fragmentcommunication.R
import com.abjt.fragmentcommunication.data.Fruit
import com.abjt.fragmentcommunication.databinding.ItemListBinding
import com.bumptech.glide.Glide

class FruitsAdapter(private val context: Context, private val onItemClicked: (Fruit) -> Unit) :
    RecyclerView.Adapter<FruitsAdapter.FruitViewHolder>() {

    private var fruits: List<Fruit> = listOf()

    fun updateDataset(fruits: List<Fruit>) {
        this.fruits = fruits
        notifyItemRangeChanged(0, fruits.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
            return FruitViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.bind(fruits[position])
    }

    override fun getItemCount(): Int = fruits.size

    inner class FruitViewHolder(private val itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root) {

        init {
            itemListBinding.root.setOnClickListener {
                onItemClicked(fruits[adapterPosition])
            }
        }

        fun bind(fruit: Fruit) {
            itemListBinding.tvFruitName.text = fruit.name
            Glide.with(context).load(fruit.icon).placeholder(R.drawable.fruits_placeholder)
                .into(itemListBinding.ivFruitIcon)
        }
    }
}