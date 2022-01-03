package com.deluxe1.generic_tableview.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.deluxe1.generic_tableview.*
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.listener.*
import com.deluxe1.generic_tableview.row_type.ActionTypeDetector
import com.deluxe1.generic_tableview.viewholder.*

/**Binds list of elements that extend from [GenericListElement]. It should be possible to add only one type of extension,
 * to retain the visual organization.
 * @property maxColumns - defines the maximum number of columns a row can have
 * @property showHeader - if header view with the column titles should be shown*/
class GenericListAdapter<T : GenericListElement>(private val maxColumns : Int, private val showHeader: Boolean,
                                                 private val onRowClickListener: OnRowClickListener<T>? = null,
                                                 private val onRowActionsListener : OnRowActionsListener<T>? = null,
                                                 onItemSelectedListener: OnItemSelectedListener<T>? = null,
                                                 @ColorRes highlightColorResId : Int = R.color.av_deep_orange,
                                                 val alternateColoring : Boolean = false,
                                                 private val actionTypeDetector: ActionTypeDetector = ActionTypeDetector()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), SelectableItemAdapter<T>{

    private val elements = mutableListOf<T>()
    private val itemSelector : ItemSelector<T>? = if (onItemSelectedListener != null) ItemSelector(onItemSelectedListener, highlightColorResId) else null
    private var onBindHeaderListener : OnBindHeaderListener<T>? = null
    private var headerView : ViewGroup? = null

    fun setHeaderView(headerView : ViewGroup) {
        this.headerView = headerView
        onBindHeaderListener = HeaderInstantiator(headerView.context, headerView, alternateColoring, actionTypeDetector)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        actionTypeDetector.getActionTypeForInt(viewType).getViewHolder(
            GenericViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onRowActionsListener,
            maxColumns
        )

    override fun getItemViewType(position: Int): Int = elements[position].type.getIntValue()


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? GenericViewHolder<T>)?.let {
            it.bindView(elements[position], false, position, alternateColoring = alternateColoring)
            itemSelector?.onBindView(holder.binding.container,position, elements[position], onRowClickListener, alternateColoring)
        }
        if (position == 0 && showHeader) onBindHeaderListener?.onHeaderBound(elements[position], getItemViewType(position), maxColumns)

    }

    override fun getItemCount() = elements.size

    fun setAdapterData(data: List<T>){
        elements.clear()
        elements.addAll(data)
        notifyDataSetChanged()
    }

    fun insertItem(item : T, position: Int) {
        elements.add(position, item)
        notifyItemInserted(position)
    }

    fun changeItem(item : T, position: Int) {
        elements[position] = item
        notifyItemChanged(position)
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
