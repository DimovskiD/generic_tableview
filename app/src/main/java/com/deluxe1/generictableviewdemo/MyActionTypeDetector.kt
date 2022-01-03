package com.deluxe1.generictableviewdemo

import com.deluxe1.generic_tableview.ActionType
import com.deluxe1.generic_tableview.row_type.ActionTypeDetector

class MyActionTypeDetector : ActionTypeDetector() {

    override fun getActionTypeForInt(value: Int): ActionType =
        if (value == MyActionType.getIntValue()) MyActionType
        else super.getActionTypeForInt(value)
}
