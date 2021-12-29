package com.deluxe1.generictableviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deluxe1.generic_tableview.adapter.GenericListAdapter
import com.deluxe1.generic_tableview.RowAction
import com.deluxe1.generic_tableview.listener.OnItemSelectedListener
import com.deluxe1.generic_tableview.listener.OnRowActionsListener
import com.deluxe1.generictableviewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnRowActionsListener<PackagePlanItem>,
    OnItemSelectedListener<PackagePlanItem> {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var adapter : GenericListAdapter<PackagePlanItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adapter = GenericListAdapter(3, true,
            onRowActionsListener = this, onItemSelectedListener = this)
        binding.recycler.setAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()
        adapter.setAdapterData(arrayListOf(PackagePlanItem("test", 2, 3f),
            PackagePlanItem("test", 2, 3f),))
    }

    /**Invoked when an action was performed on a table row
     * @param element - the element that corresponds to the clicked row
     * @param action - the action that was performed. One of [RowAction]*/
    override fun onAction(element: PackagePlanItem, action: RowAction) {
    }

    override fun onItemSelected(item: PackagePlanItem, isSelected: Boolean, totalSelected: Int) {

    }
}