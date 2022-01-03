package com.deluxe1.generic_tableview.sample

import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.view.CustomBooleanView
import com.deluxe1.generic_tableview.view.CustomSwitchView
import com.deluxe1.generic_tableview.view.CustomTextView
import java.util.*

/**Sample list element*/
class UserElement(
    firstName: String,
    lastName: String,
    phoneNumber: String,
    email: String,
    approved: Boolean,
    verified: Boolean,
    createdAt: Date,
    lastModified: Date
) : GenericListElement(mapOf(
    CustomTextView("First name", firstName) to true,
    CustomTextView("Last name", lastName) to true,
    CustomTextView("Phone number", phoneNumber, 1.5f) to true,
    CustomTextView("Email", email) to false,
    CustomSwitchView("Status", approved) to true,
    CustomBooleanView("Verified", verified) to true,
    CustomTextView("Created at", createdAt.toString()) to false,
    CustomTextView("Modified at", lastModified.toString()) to false))