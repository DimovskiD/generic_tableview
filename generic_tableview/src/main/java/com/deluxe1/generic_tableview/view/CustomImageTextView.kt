package com.deluxe1.generic_tableview.view

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.deluxe1.generic_tableview.R
import kotlin.math.roundToInt

class CustomImageTextView private constructor(
    private val value: String,
    private val weight: Float = 1f,
    @DrawableRes private val imageResource: Int?,
    private val imageUrl: String?,
    private val circleCrop: Boolean
) : GenericView() {

    constructor(title: String, value: String, weight: Float = 1f, @DrawableRes imageResource: Int, circleCrop: Boolean = false) : this(value, weight, imageResource, null, circleCrop) {
        this.title = title
    }

    constructor(@StringRes titleResId: Int, value: String, weight: Float = 1f, @DrawableRes imageResource: Int, circleCrop: Boolean = false) : this(value, weight, imageResource, null, circleCrop) {
        this.titleResId = titleResId
    }

    constructor(title: String, value: String, weight: Float = 1f, imageUrl : String, circleCrop: Boolean = false) : this(value, weight, null, imageUrl, circleCrop) {
        this.title = title
    }

    constructor(@StringRes titleResId: Int, value: String, weight: Float = 1f, imageUrl : String, circleCrop: Boolean = false) : this(value, weight, null, imageUrl, circleCrop) {
        this.titleResId = titleResId
    }

    /**@returns the view for the given element*/
    override fun getView(context: Context, isHeader: Boolean): View {
        if (isHeader) return CustomTextView(getTitle(context), value).getView(context, isHeader)
        val linearLayout = LinearLayout(context).apply { orientation = LinearLayout.HORIZONTAL }
        val imageView = ImageView(context)
        var requestBuilder = Glide.with(context).load(imageResource?:imageUrl)
        if (circleCrop) requestBuilder = requestBuilder.circleCrop()
        requestBuilder.into(imageView)
        linearLayout.addView(imageView, getImageViewParams(context, null))
        linearLayout.addView(CustomTextView(getTitle(context), value).getView(context, isHeader), LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply { gravity = Gravity.CENTER })
        return linearLayout
    }

    override fun getLayoutParams(context: Context): LinearLayout.LayoutParams {
        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weight)
        params.gravity = Gravity.CENTER
        val margin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 5f, context.resources.displayMetrics
        ).roundToInt()
        params.setMargins(margin, margin, margin, margin)
        return params
    }

    private fun getImageViewParams(context: Context, width: Int?) : LinearLayout.LayoutParams {
        val dimension = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 20f, context.resources.displayMetrics
        ).roundToInt()
        val params = LinearLayout.LayoutParams(width?:dimension, dimension)
        params.gravity = Gravity.CENTER
        val margin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 3f, context.resources.displayMetrics
        ).roundToInt()

        params.setMargins(margin, margin, margin, margin)
        return params
    }
}