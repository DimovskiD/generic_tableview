package com.deluxe1.generic_tableview.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.deluxe1.generic_tableview.*
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.listener.*
import com.deluxe1.generic_tableview.viewholder.*

/**Binds list of elements that extend from [GenericListElement]. It should be possible to add only one type of extension,
 * to retain the visual organization.
 * @property maxColumns - defines the maximum number of columns a row can have
 * @property showHeader - if header view with the column titles should be shown*/
class GenericListAdapter<T : GenericListElement>(private val maxColumns : Int, private val showHeader: Boolean,
                                                 private val onRowClickListener: OnRowClickListener<T>? = null,
                                                 private val onRowActionsListener : OnRowActionsListener<T>? = null,
                                                 onItemSelectedListener: OnItemSelectedListener<T>? = null,
                                                 @ColorRes highlightColorResId : Int = R.color.av_deep_orange
                                                 ) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), SelectableItemAdapter<T>{

    private val elements = mutableListOf<T>()
    private val itemSelector : ItemSelector<T>? = if (onItemSelectedListener != null) ItemSelector(onItemSelectedListener, highlightColorResId) else null
    private var onBindHeaderListener : OnBindHeaderListener<T>? = null
    private var headerView : ViewGroup? = null

    fun setHeaderView(headerView : ViewGroup) {
        this.headerView = headerView
        onBindHeaderListener = HeaderInstantiator(headerView.context, headerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when (viewType) {
           RowType.CHEVRON.ordinal -> ChevronViewHolder<T>(GenericViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false), maxColumns)
           RowType.BUTTON.ordinal -> ButtonViewHolder(GenericViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false), maxColumns, onRowActionsListener)
           RowType.POSITIVE_NEGATIVE.ordinal -> DoubleButtonViewHolder(GenericViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false), maxColumns, onRowActionsListener)
           else -> NoActionViewHolder<T>(GenericViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false), maxColumns)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return elements[position].type.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? GenericViewHolder<T>)?.let {
            it.bindView(elements[position], false)
            itemSelector?.onBindView(holder.binding.container,position, elements[position], onRowClickListener)
        }
        if (position == 0 && showHeader) onBindHeaderListener?.onHeaderBound(elements[position], getItemViewType(position), maxColumns)

    }

    override fun getItemCount() = elements.size

    fun setAdapterData(data: List<T>){
        elements.clear()
        elements.addAll(data)
        notifyDataSetChanged()
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
            lst.add(elements[it])
        }
        return lst
    }

}
