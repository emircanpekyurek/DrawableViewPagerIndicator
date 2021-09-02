package com.pekyurek.emircan

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class DummyViewPagerAdapter : PagerAdapter() {
    override fun getCount(): Int {
        return 5
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return true
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return TextView(container.context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
            text = "$position"
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (`object` as? View)?.let { container.removeView(it) }
    }
}