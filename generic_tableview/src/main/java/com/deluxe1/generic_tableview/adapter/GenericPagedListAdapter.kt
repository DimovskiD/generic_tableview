package com.deluxe1.generic_tableview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.deluxe1.generic_tableview.*
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.listener.*
import com.deluxe1.generic_tableview.row_type.ActionTypeDetector
import com.deluxe1.generic_tableview.viewholder.*

class GenericPagedListAdapter<T : GenericListElement> (private val maxDataColumns : Int,
                                                       private val showHeader: Boolean,
                                                       private val onRowClickListener: OnRowClickListener<T>? = null,
                                                       private val onRowActionsListener : OnRowActionsListener<T>? = null,
                                                       onItemSelectedListener: OnItemSelectedListener<T>? = null,
                                                       @ColorRes highlightColorResId : Int = R.color.av_deep_orange,
                                                       comparator: DiffUtil.ItemCallback<T>? = null,
                                                       private val alternateColoring : Boolean,
                                                       private val actionTypeDetector: ActionTypeDetector = ActionTypeDetector()
) :
    PagingDataAdapter<T, GenericViewHolder<T>>(comparator?:GenericComparator<T>().comparator),
    SelectableItemAdapter<T> {

    private var headerView : ViewGroup? = null
    private var onBindHeaderListener : OnBindHeaderListener<T>? = null
    private val itemSelector : ItemSelector<T>? = if (onItemSelectedListener != null) ItemSelector(onItemSelectedListener, highlightColorResId) else null


    fun setHeaderView(headerView : ViewGroup) {
        this.headerView = headerView
        onBindHeaderListener = HeaderInstantiator(headerView.context, headerView, alternateColoring, actionTypeDetector)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> =
        actionTypeDetector.getActionTypeForInt(viewType).getViewHolder(
            GenericViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onRowActionsListener,
            maxDataColumns
        )

    override fun getItemViewType(position: Int): Int  = getItem(position)?.type?.getIntValue()?: -1

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        val element = getItem(position)!!
        holder.bindView(element, false, position, alternateColoring)
        if (position == 0 && showHeader)
            onBindHeaderListener?.onHeaderBound(getItem(position)!!, getItemViewType(position), maxDataColumns)
        itemSelector?.onBindView(holder.binding.container,position, element, onRowClickListener, alternateColoring)
    }

    override fun clearSelection() : Boolean {
        val lst = itemSelector?.clearSelection()
        lst?.forEach{
            notifyItemChanged(it)
        }
        return !lst.isNullOrEmpty()
    }

    override fun getSelectedItems(): List<T> {
        val lst = arrayListOf<T>()
        itemSelector?.selectedItems?.forEach {
            lst.add(getItem(it)!!)
        }
        return lst
    }

}

class GenericComparator<T> : DiffUtil.ItemCallback<T>() {
    val comparator: DiffUtil.ItemCallback<T> = this

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        // Id is unique.
        return oldItem?.equals(newItem)?:false
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return false //todo
    }

}