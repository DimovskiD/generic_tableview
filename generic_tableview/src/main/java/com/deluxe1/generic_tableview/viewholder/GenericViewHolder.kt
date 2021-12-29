package com.deluxe1.generic_tableview.viewholder

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.R
import com.deluxe1.generic_tableview.listener.OnRowClickListener
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding

/**A view holder that presents a row in the table view with a view provided by it's children as a last column */
abstract class GenericViewHolder<T : GenericListElement>(val binding : GenericViewHolderBinding, val maxColumns : Int) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(element: T, isHeader: Boolean) : View {
        binding.container.removeAllViews()
        element.columnsMap.forEach {
            if (it.value && binding.container.childCount < maxColumns)
                binding.container.addView(it.key.getView(binding.root.context, isHeader).apply { setBackgroundColor(context.getColor(R.color.av_deep_orange))
                    (it as? LinearLayout)?.gravity = Gravity.CENTER; }, it.key.getLayoutParams(binding.container.context).apply { gravity = Gravity.CENTER})
        }
        getView(element)?.let { view ->
            val params = view.layoutParams ?: LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { gravity = Gravity.CENTER }
            if (isHeader) view.visibility = View.INVISIBLE
            binding.container.addView(view, params)
        }

        binding.container.gravity = Gravity.CENTER
        return binding.container
    }

    /**@return a view for the last column*/
    abstract fun getView(element : T): View?
}