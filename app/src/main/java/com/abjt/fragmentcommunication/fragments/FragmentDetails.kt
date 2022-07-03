package com.abjt.fragmentcommunication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abjt.fragmentcommunication.R
import com.abjt.fragmentcommunication.data.Fruit
import com.abjt.fragmentcommunication.data.ITEM_FRUIT
import com.abjt.fragmentcommunication.data.ITEM_SELECTED_FRUIT
import com.abjt.fragmentcommunication.databinding.DetailsFragmentBinding
import com.bumptech.glide.Glide

class FragmentDetails : Fragment() {

    private lateinit var detailsFragmentBinding: DetailsFragmentBinding
    private var selectedFruit: Fruit? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsFragmentBinding = DetailsFragmentBinding.inflate(inflater, container, false)
        return detailsFragmentBinding.root
    }

    //manually handling config change since there is no viewModel or we don't use dependency injection
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            it.getParcelable<Fruit>(ITEM_SELECTED_FRUIT)?.let { fruit ->
                showDetailsOf(fruit)
            }
        } ?: let {
            requireArguments().getParcelable<Fruit>(ITEM_FRUIT)?.let { showDetailsOf(it) }
        }
    }

    fun showDetailsOf(fruit: Fruit) {
        detailsFragmentBinding.apply {
            tvFruitName.text = fruit.name
            Glide.with(this@FragmentDetails).load(fruit.cover)
                .placeholder(R.drawable.fruits_placeholder).into(ivFruitCover)
        }
        selectedFruit = fruit
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        selectedFruit?.let {
            outState.putParcelable(ITEM_SELECTED_FRUIT, it)
        }
    }

    companion object {
        fun instance(fruit: Fruit) = FragmentDetails().apply {
            arguments = Bundle().apply {
                this.putParcelable(ITEM_FRUIT, fruit)
            }
        }
    }
}