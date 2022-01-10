package com.deluxe1.generictableviewdemo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.deluxe1.generic_tableview.RowAction
import com.deluxe1.generic_tableview.adapter.GenericListAdapter
import com.deluxe1.generic_tableview.listener.OnItemSelectedListener
import com.deluxe1.generic_tableview.listener.OnRowActionsListener
import com.deluxe1.generictableviewdemo.model.Employee
import com.deluxe1.generictableviewdemo.databinding.ActivityMainBinding
import com.deluxe1.generictableviewdemo.model.Client
import com.deluxe1.generictableviewdemo.table_view_components.MyActionTypeDetector
import java.io.InputStream

class ClientActivity : AppCompatActivity(), OnRowActionsListener<Client> {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapter : GenericListAdapter<Client>
    private var employeeId: Int? = null
    private var menuItem: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        employeeId = intent.getIntExtra("EMPLOYEE_ID", -1)
        adapter = GenericListAdapter(
            3,
            true,
            onRowActionsListener = this,
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
        adapter.setAdapterData(getClients())
    }

    private fun getClients(): List<Client> {
        val clients = arrayListOf<Client>()
        val ins: InputStream = resources.openRawResource(
            resources.getIdentifier(
                "client_list",
                "raw", packageName
            )
        )
        ins.bufferedReader().forEachLine { line ->
            val lineValues = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*\$)".toRegex()).map { it.trim() }
            val assignedToId = lineValues[3]
            if (assignedToId.toInt() == employeeId)
                clients.add(Client(id = lineValues[0].toInt(), company = lineValues[1], phone = lineValues[2], country = lineValues[4], avatar = lineValues[5]))
        }
        return clients
    }


    private fun resetActionBar() {
        title = getString(R.string.clients_for, intent.getStringExtra("EMPLOYEE_NAME"))
        menuItem?.isVisible = false
    }

    /**Invoked when an action was performed on a table row
     * @param element - the element that corresponds to the clicked row
     * @param action - the action that was performed. One of [RowAction]*/
    override fun onAction(element: Client, action: RowAction) {

    }
}