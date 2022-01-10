package com.deluxe1.generic_tableview.data

/**All models used with the [com.deluxe1.generic_tableview.adapter.GenericPagedListAdapter] should implement this interface
 * and provide the three properties for it to function properly
 * @property page - the page that is being loaded
 * @property pageCount - the total number of pages
 * @property data - data returned for the current page*/
interface GenericPagedResponse<T> {
    var page: Int
    var pageCount: Int
    var data: Array<T>
}
