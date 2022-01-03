package com.deluxe1.generic_tableview.row_type

import com.deluxe1.generic_tableview.ActionType

/**Detects the [ActionType] based on the input integer
 * Extend this class if you want to add your own ActionTypes*/
open class ActionTypeDetector {

    /**@param value - integer value of the [ActionType] - used for item type detection in a RecyclerView
     * @return the [ActionType] instance that corresponds to the input, if any. If not found, returns [GenericRowType]*/
    open fun getActionTypeForInt(value : Int) : ActionType =  when (value) {
            GenericRowType.getIntValue() -> GenericRowType
            ButtonRowType.getIntValue() -> ButtonRowType
            PositiveNegativeRowType.getIntValue() -> PositiveNegativeRowType
            ChevronRowType.getIntValue() -> ChevronRowType
            else -> GenericRowType
        }
}