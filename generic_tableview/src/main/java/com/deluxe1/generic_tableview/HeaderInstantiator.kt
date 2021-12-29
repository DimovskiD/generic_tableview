package com.deluxe1.generic_tableview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.size
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.listener.OnBindHeaderListener
import com.deluxe1.generic_tableview.viewholder.ButtonViewHolder
import com.deluxe1.generic_tableview.viewholder.ChevronViewHolder
import com.deluxe1.generic_tableview.viewholder.DoubleButtonViewHolder
import com.deluxe1.generic_tableview.viewholder.NoActionViewHolder

/**Instantiates a sticky header for [GenericListElement]
 * @property context - Context instance to inflate the layout
 * @property headerView - View where the header should be added -> usually right above the corresponding RecyclerView*/
class HeaderInstantiator<T : GenericListElement> (
    val context : Context, val headerView : ViewGroup
        ) : OnBindHeaderListener<T>  {

    /**Called when the first element of the adapter has been instantiated
     * Triggers creating the header
     * @param item - a sample item from the adapter - used to calculate the positioning of the header titles
     * @param viewType - type of the views in the adapter - used to position the headers vertically
     * @param maxColumns - maximum number of columns (header will have maxNumber - 1 - for the action element)*/
    override fun onHeaderBound(item: T, viewType: Int, maxColumns : Int) {
        val binding = GenericViewHolderBinding.inflate(LayoutInflater.from(context))
        val view = when(viewType) {
            RowType.BUTTON.ordinal -> ButtonViewHolder<T>(binding, maxColumns, null)
            RowType.CHEVRON.ordinal -> ChevronViewHolder(binding,  maxColumns)
            RowType.POSITIVE_NEGATIVE.ordinal -> DoubleButtonViewHolder(binding,  maxColumns, null)
            else -> NoActionViewHolder(binding,  maxColumns)
        }
        if (headerView.size == 0)
            headerView.addView(view.bindView(item, true))
    }
}