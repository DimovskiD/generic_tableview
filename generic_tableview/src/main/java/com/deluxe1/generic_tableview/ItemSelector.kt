package com.deluxe1.generic_tableview

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.graphics.ColorUtils
import com.deluxe1.generic_tableview.listener.OnItemSelectedListener
import com.deluxe1.generic_tableview.listener.OnRowClickListener

class ItemSelector<T : GenericListElement>(private val onItemSelectedListener: OnItemSelectedListener<T>, @ColorRes private val colorRes : Int) {
    val selectedItems = arrayListOf<Int>()
    private var selectionMode = false


    fun onBindView(container: ViewGroup, position : Int, element : T, onRowClickListener: OnRowClickListener<T>?, alternateColoring : Boolean = false) {
        container.setBackgroundColor(getBackgroundColor(container.context, position, alternateColoring))

        container.setOnLongClickListener {
            selectionMode = true
            if (!selectedItems.contains(position))
                setSelected(container, position, element, true,alternateColoring)
            true
        }

        container.setOnClickListener {
            if (!selectionMode) onRowClickListener?.onRowClicked(element)
            else if (selectedItems.contains(position)) setSelected(container, position, element, false, alternateColoring)
            else setSelected(container, position, element, true, alternateColoring)
        }
    }

    /**Compute the background color based on the item selection status and position
     * @param context - the context used to get color from resources
     * @param position - the position of the element - important if using alternate row coloring
     * @param alternateColoring - if true every second row has a slightly different variation of the color for better visual separation
     * @return the Color to be used for background*/
    private fun getBackgroundColor(context: Context, position: Int, alternateColoring: Boolean): Int =
            if (selectedItems.contains(position)) {
                if (alternateColoring)
                    ColorUtils.blendARGB(
                        context.getColor(
                            colorRes
                        ), context.getColor(
                            if (position % 2 == 0 && position >= 0) R.color.transparent_darker
                            else R.color.transparent_lighter
                        ), 0.05f
                    )
                else context.getColor(colorRes)
            }
            else if (alternateColoring)
                if (position % 2 == 0 && position >= 0)  context.getColor(R.color.transparent_darker)
                else  context.getColor(R.color.transparent_lighter)
            else context.getColor(android.R.color.transparent)



    private fun setSelected(container: ViewGroup, position: Int, element: T, select: Boolean, alternateColoring: Boolean) {
        if (select) {
            selectedItems.add(position)
            onItemSelectedListener.onItemSelected(element, true, selectedItems.size)
        } else {
            selectedItems.remove(position)
            if (selectedItems.isEmpty()) selectionMode = false
        }
        selectedItems.sort()
        container.setBackgroundColor(getBackgroundColor(container.context, position, alternateColoring))
        onItemSelectedListener.onItemSelected(element, select, selectedItems.size)
    }

    fun clearSelection() : List<Int> {
        val lst = selectedItems.toList()
        selectionMode = false
        selectedItems.clear()
        return lst
    }
}