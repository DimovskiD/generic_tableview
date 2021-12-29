package com.deluxe1.generic_tableview.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deluxe1.generic_tableview.adapter.GenericListAdapter
import com.deluxe1.generic_tableview.R
import com.deluxe1.generic_tableview.adapter.GenericPagedListAdapter

class HeaderRecyclerView : ConstraintLayout {

    private lateinit var recyclerView: RecyclerView
    private lateinit var header : LinearLayout

    constructor(context: Context) : super(context) {
        initViews();
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initViews()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initViews()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initViews()
    }

    fun setAdapter(adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView.adapter = adapter
        (adapter as? GenericListAdapter<*>)?.setHeaderView(header)
        (adapter as? GenericPagedListAdapter<*>)?.setHeaderView(header)
    }

    private fun initViews() {
        inflate(context, R.layout.header_recyclerview, this)
        recyclerView = getChildAt(1) as RecyclerView
        header = getChildAt(0) as LinearLayout
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    fun setLayoutManager(layoutManager: LinearLayoutManager) { recyclerView.layoutManager = layoutManager}


}