package com.deluxe1.generic_tableview

import com.deluxe1.generic_tableview.view.GenericView

/**Main class for defining the elements of a [GenericListElement].
 * All elements of the table must extend from this class!
 * provides a map of the views contained by the element, as well as information if the property is of high importance
 * and should be shown in the general table, or if it is considered a detail and should not be shown in the table
 * @property columnsMap - map of columns that one table row should have.
 * @property type - type of the row that should be used for this element. One of [RowType]. Defaults to [RowType.CHEVRON]
 * @property actionTextRes - string resource id for the action element
 * The key is the view that corresponds to the element
 * The value indicates if the user should be added to the main table, or passed along as a detail*/
open class GenericListElement(val columnsMap : Map<GenericView, Boolean>, val type : RowType = RowType.CHEVRON, val actionTextRes : Int? = null, val actionIconRes : Int? = null)