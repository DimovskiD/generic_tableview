package com.deluxe1.generic_tableview.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenericPagedResponse<T> (
    @SerializedName("count")
    @Expose
    var count: Int = 0,
    @SerializedName("total")
    @Expose
    var total: Int = 0,
    @SerializedName("page")
    @Expose
    var page: Int = 0,
    @SerializedName("pageCount")
    @Expose
    var pageCount: Int = 0,
    @SerializedName("data")
    @Expose
    var data: Array<T>
)