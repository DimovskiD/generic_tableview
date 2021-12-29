package com.deluxe1.generic_tableview.data

import com.deluxe1.generic_tableview.GenericListElement

interface GenericEndpoint<T : GenericListElement> {
    suspend fun getPagedResponse(page : Int, size : Int) : GenericPagedResponse<T>
}