package com.deluxe1.generictableviewdemo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.deluxe1.generic_tableview.RowAction
import com.deluxe1.generic_tableview.adapter.GenericListAdapter
import com.deluxe1.generic_tableview.listener.OnItemSelectedListener
import com.deluxe1.generic_tableview.listener.OnRowActionsListener
import com.deluxe1.generic_tableview.listener.OnRowClickListener
import com.deluxe1.generictableviewdemo.model.Employee
import com.deluxe1.generictableviewdemo.databinding.ActivityMainBinding
import com.deluxe1.generictableviewdemo.table_view_components.MyActionTypeDetector
import java.io.InputStream

class MainActivity : AppCompatActivity(), OnRowClickListener<Employee>,
    OnItemSelectedListener<Employee>, OnRowActionsListener<Employee> {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapter : GenericListAdapter<Employee>
    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adapter = GenericListAdapter(
            maxDataColumns = 3,
            showHeader = true,
            onItemSelectedListener = this,
            onRowActionsListener = this,
            onRowClickListener = this,
            actionTypeDetector = MyActionTypeDetector(),
            highlightColorResId = R.color.teal_a700,
            alternateColoring = true
        )
        binding.recycler.setAdapter(adapter)
        resetActionBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menuItem = menu?.findItem(R.id.remove_all)
        menuItem?.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.remove_all) {
            adapter.removeSelectedItems()
            resetActionBar()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        adapter.setAdapterData(getEmployees())
    }

    private fun getEmployees(): List<Employee> {
        val employees = arrayListOf<Employee>()
        val ins: InputStream = resources.openRawResource(
            resources.getIdentifier(
                "employees",
                "raw", packageName
            )
        )
        ins.bufferedReader().forEachLine { line ->
            val lineValues = line.split(",").map { it.trim() }
            employees.add(Employee(lineValues[0].toInt(), lineValues[1],lineValues[2], lineValues[3].toBooleanStrict()))
        }
        return employees
    }


    private fun resetActionBar() {
        title = getString(R.string.employee_list)
        menuItem?.isVisible = false
    }

    override fun onRowClicked(row: Employee) {
        startActivity(Intent(this, ClientActivity::class.java).apply {
            putExtra("EMPLOYEE_ID", row.id)
            putExtra("EMPLOYEE_NAME", "${row.firstName} ${row.lastName}")
        })
    }

    override fun onItemSelected(item: Employee, isSelected: Boolean, totalSelected: Int) {
        if (totalSelected > 0) {
            menuItem?.isVisible = true
            title = "$totalSelected Selected"
        } else {
            menuItem?.isVisible = false
            resetActionBar()
        }
    }

    /**Invoked when an action was performed on a table row
     * @param element - the element that corresponds to the clicked row
     * @param action - the action that was performed. One of [RowAction]*/
    override fun onAction(element: Employee, action: RowAction) {
        adapter.removeItem(element)
    }

}