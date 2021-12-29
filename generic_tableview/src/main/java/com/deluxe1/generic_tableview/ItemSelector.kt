package com.deluxe1.generic_tableview

import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.deluxe1.generic_tableview.listener.OnItemSelectedListener
import com.deluxe1.generic_tableview.listener.OnRowClickListener

class ItemSelector<T : GenericListElement>(private val onItemSelectedListener: OnItemSelectedListener<T>, @ColorRes private val colorRes : Int) {
    val selectedItems = arrayListOf<Int>()
    private var selectionMode = false


    fun onBindView(container: ViewGroup, position : Int, element : T, onRowClickListener: OnRowClickListener<T>?) {
        container.setBackgroundColor(container.context.getColor(
            if (selectedItems.contains(position)) colorRes else android.R.color.transparent))

        container.setOnLongClickListener {
            selectionMode = true
            if (!selectedItems.contains(position))
                setSelected(container, position, element, true)
            true
        }

        container.setOnClickListener {
            if (!selectionMode) onRowClickListener?.onRowClicked(element)
            else if (selectedItems.contains(position)) setSelected(container, position, element, false)
            else setSelected(container, position, element, true)
        }
    }

    private fun setSelected(container: ViewGroup, position: Int, element: T, select: Boolean) {
        if (select) {
            selectedItems.add(position)
            container.setBackgroundColor(
                container.context.getColor(
                    colorRes
                )
            )
            onItemSelectedListener.onItemSelected(element, true, selectedItems.size)
        } else {
            selectedItems.remove(position)
            if (selectedItems.isEmpty()) selectionMode = false
            container.setBackgroundColor(
                container.context.getColor(
                    android.R.color.transparent
                )
            )
        }
        onItemSelectedListener.onItemSelected(element, select, selectedItems.size)
    }

    fun clearSelection() : List<Int> {
        val lst = selectedItems.toList()
        selectionMode = false
        selectedItems.clear()
        return lst
    }
}