package com.deluxe1.generic_tableview.viewholder

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.R
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding

/**A view holder that presents a row in the table view with a view provided by it's children as a last column */
abstract class GenericViewHolder<T : GenericListElement>(val binding : GenericViewHolderBinding, private val maxColumns : Int) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(element: T, isHeader: Boolean, position : Int, alternateColoring : Boolean) : View {
        binding.container.removeAllViews()
        element.columnsMap.forEach { entry ->
            if (entry.value && binding.container.childCount < maxColumns) {
                val view = entry.key.getView(binding.root.context, isHeader)
                binding.container.addView(view,
                    entry.key.getLayoutParams(binding.container.context)
                        .apply { gravity = Gravity.CENTER })
            }
        }
        getView(element)?.let { view ->
            val params = view.layoutParams ?: LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { gravity = Gravity.CENTER }
            if (isHeader) view.visibility = View.INVISIBLE
            binding.container.addView(view, params)
        }

        if (alternateColoring)
            binding.container.setBackgroundColor(
                binding.container.context.getColor(
                    if (position % 2 == 0 && position >= 0) R.color.transparent_darker else R.color.transparent_lighter
                )
            )

        binding.container.gravity = Gravity.CENTER
        return binding.container
    }

    /**@return a view for the last column*/
    abstract fun getView(element : T): View?
}