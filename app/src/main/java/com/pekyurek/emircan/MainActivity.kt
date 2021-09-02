package com.pekyurek.emircan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.pekyurek.emircan.indicator.DrawableViewPagerIndicator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager2()
        initViewPager()
    }

    private fun initViewPager2() {
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        val viewPager2Indicator = findViewById<DrawableViewPagerIndicator>(R.id.viewPager2Indicator)

        viewPager2.adapter = DummyRecyclerAdapter()
        viewPager2Indicator.setViewPager(viewPager2)
    }

    private fun initViewPager() {
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val viewPagerIndicator = findViewById<DrawableViewPagerIndicator>(R.id.viewPagerIndicator)


        viewPager.adapter = DummyViewPagerAdapter() //or DummyFragmentStatePagerAdapter(supportFragmentManager)
        viewPagerIndicator.setViewPager(viewPager)

    }


}