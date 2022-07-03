package com.abjt.fragmentcommunication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.abjt.fragmentcommunication.data.*
import com.abjt.fragmentcommunication.databinding.ActivityHomeBinding
import com.abjt.fragmentcommunication.fragments.FragmentDetails
import com.abjt.fragmentcommunication.fragments.FragmentList

class HomeActivity : AppCompatActivity(), FragmentList.ItemSelectedCallback {

    private lateinit var binding: ActivityHomeBinding

    private var fragmentList: Fragment? = null
    private var fragmentDetails: Fragment? = null
    private val mSupportFragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        savedInstanceState?.let {
            findFragments()
        } ?: initFragment()
        setContentView(binding.root)
    }

    private fun initFragment() {
        fragmentList = FragmentList.instance()
        doTransaction(fragmentList as Fragment, R.id.fl_fragment_list_container, FRAGMENT_LIST_TAG)
    }

    private fun findFragments() {
        fragmentList = mSupportFragmentManager.findFragmentByTag(FRAGMENT_LIST_TAG)
        fragmentDetails = mSupportFragmentManager.findFragmentByTag(FRAGMENT_DETAILS_TAG)
    }

    private fun doTransaction(fragment: Fragment, containerId: Int, tag: String) {
        mSupportFragmentManager.beginTransaction().add(containerId, fragment, tag).commit()
    }

    override fun onItemSelected(item: Fruit) {
        fragmentDetails?.let {
            (it as FragmentDetails).showDetailsOf(item)
        } ?: let {
            fragmentDetails = FragmentDetails.instance(item)
            doTransaction(fragmentDetails as Fragment, R.id.fl_fragment_details_container, FRAGMENT_DETAILS_TAG)
        }
    }
}