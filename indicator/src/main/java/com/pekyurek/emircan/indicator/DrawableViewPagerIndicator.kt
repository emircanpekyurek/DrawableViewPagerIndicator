package com.pekyurek.emircan.indicator

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class DrawableViewPagerIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyle: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var indicatorDrawable: Drawable? = null
    private val selectedIndicatorDrawable: Drawable? by lazy {
        indicatorDrawable?.constantState?.newDrawable()?.mutate()?.apply {
            setTint(selectedIndicatorColor)
        }
    }
    private var selectedIndicatorColor: Int = 0
    private var unSelectedIndicatorColor: Int = 0
    private var indicatorWidth = 0
    private var indicatorHeight = 0
    private var indicatorMargin = 0
    private var indicatorScaleX = 1F
    private var indicatorScaleY = 1F

    private var lastSelectedView: View? = null

    init {
        initLayout()
        setData(context.obtainStyledAttributes(attrs,
            R.styleable.DrawableViewPagerIndicator,
            defStyleAttr,
            defStyle))
    }

    private fun initLayout() {
        clipToPadding = false
        orientation = HORIZONTAL
    }

    private fun setData(typedArray: TypedArray) = typedArray.run {
        indicatorDrawable = getDrawable(R.styleable.DrawableViewPagerIndicator_indicator_drawable)
            ?: contextDrawable(R.drawable.default_viewpager_indicator)
        selectedIndicatorColor =
            getColor(R.styleable.DrawableViewPagerIndicator_indicator_selected_color,
                ContextCompat.getColor(context, R.color.default_selected_indicator_color))
        unSelectedIndicatorColor =
            getColor(R.styleable.DrawableViewPagerIndicator_indicator_unselected_color,
                ContextCompat.getColor(context, R.color.default_unselected_indicator_color))
        indicatorWidth =
            getDimensionPixelSize(R.styleable.DrawableViewPagerIndicator_indicator_width,
                context.resources.getDimensionPixelSize(R.dimen.default_indicator_width))
        indicatorHeight =
            getDimensionPixelSize(R.styleable.DrawableViewPagerIndicator_indicator_height,
                context.resources.getDimensionPixelSize(R.dimen.default_indicator_height))
        indicatorMargin =
            getDimensionPixelSize(R.styleable.DrawableViewPagerIndicator_indicator_margin,
                context.resources.getDimensionPixelSize(R.dimen.default_indicator_margin))
        indicatorScaleX =
            getFloat(R.styleable.DrawableViewPagerIndicator_indicator_scale_x,
                ResourcesCompat.getFloat(context.resources, R.dimen.default_indicator_scale_x))
        indicatorScaleY =
            getFloat(R.styleable.DrawableViewPagerIndicator_indicator_scale_y,
                ResourcesCompat.getFloat(context.resources, R.dimen.default_indicator_scale_y))
        setUnselectedDrawableTint()
        setScalePadding()
        recycle()
    }

    private fun contextDrawable(@DrawableRes drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableRes)
    }

    private fun setUnselectedDrawableTint() {
        indicatorDrawable?.setTint(unSelectedIndicatorColor)
    }

    private fun setScalePadding() {
        var verticalPadding = 0
        var horizontalPadding = 0
        if (indicatorScaleX > 1) {
            horizontalPadding = (indicatorWidth * indicatorScaleX).toInt()
        }
        if (indicatorScaleY > 1) {
            verticalPadding = (indicatorHeight * indicatorScaleY).toInt()
        }
        setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
    }

    fun setViewPager(viewPager: ViewPager2) {
        viewPager.adapter?.let { adapter ->
            createDots(adapter.itemCount, viewPager.currentItem)
            setOnPageListener(viewPager)
        } ?: throw Exception("ViewPager adapter is null")
    }

    private fun createDots(itemCount: Int, selectedItemPosition: Int) {
        (0 until itemCount).forEach { addView(createDotView(selectedItemPosition == it)) }
    }

    private fun setOnPageListener(viewPager: ViewPager2) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                fillThisPosition(position)
            }
        })
    }

    private fun fillThisPosition(position: Int) {
        lastSelectedView?.let { unSelectDot(it) }
        getChildAt(position)?.let { selectDot(it) }
    }

    private fun selectDot(dotView : View) {
        dotView.background = selectedIndicatorDrawable
        dotView.animate().scaleX(indicatorScaleX).scaleY(indicatorScaleY)
        lastSelectedView = dotView
    }

    private fun unSelectDot(dotView: View) {
        dotView.animate().scaleX(1f).scaleY(1F)
        dotView.background = indicatorDrawable
    }

    private fun createDotView(selectedItem: Boolean): View {
        return View(context).apply {
            id = generateViewId()
            layoutParams = LayoutParams(indicatorWidth, indicatorHeight).apply {
                marginEnd = indicatorMargin
                marginStart = indicatorMargin
            }

            if (selectedItem) selectDot(this) else unSelectDot(this)
        }
    }

    fun setViewPager(viewPager: ViewPager) {
        if (viewPager.adapter == null) {
            throw Exception("ViewPager adapter is null")
        }
        createDots(viewPager.adapter!!.count, viewPager.currentItem)
        setOnPageListener(viewPager)
    }

    private fun setOnPageListener(viewPager: ViewPager) {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                fillThisPosition(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }


}