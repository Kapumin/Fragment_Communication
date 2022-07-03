package com.abjt.fragmentcommunication.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abjt.fragmentcommunication.adapter.FruitsAdapter
import com.abjt.fragmentcommunication.data.DataHelper
import com.abjt.fragmentcommunication.data.Fruit
import com.abjt.fragmentcommunication.databinding.ListFragmentBinding

class FragmentList : Fragment() {

    private lateinit var binding: ListFragmentBinding
    private lateinit var fruitsAdapter: FruitsAdapter
    private lateinit var itemSelectedCallback: ItemSelectedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemSelectedCallback = context as ItemSelectedCallback
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fruitsAdapter = FruitsAdapter(requireContext()) { fruit ->
            itemSelectedCallback.onItemSelected(fruit)
        }.also {
            it.updateDataset(DataHelper.getFruits())
            binding.rvFruits.adapter = it
        }
    }

    interface ItemSelectedCallback {
        fun onItemSelected(item: Fruit)
    }

    companion object {
        fun instance() = FragmentList()
    }
}