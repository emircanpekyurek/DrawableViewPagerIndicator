package com.pekyurek.emircan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class DummyFragmentStatePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        return Fragment()
    }

}