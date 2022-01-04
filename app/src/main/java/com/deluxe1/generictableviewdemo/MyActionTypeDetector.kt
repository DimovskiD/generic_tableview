package com.deluxe1.generictableviewdemo

import com.deluxe1.generic_tableview.ActionType
import com.deluxe1.generic_tableview.row_type.ActionTypeDetector

class MyActionTypeDetector : ActionTypeDetector() {

    override fun getActionTypeForInt(value: Int): ActionType =
        when (value) {
            MyActionType.getIntValue() -> MyActionType
            CustomButtonActionType.getIntValue() -> CustomButtonActionType
            else -> super.getActionTypeForInt(value)
        }
}
