package com.deluxe1.generic_tableview.data

interface GenericPagedResponse<T> {
    var page: Int
    var pageCount: Int
    var data: Array<T>
}
