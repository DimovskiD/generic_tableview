package com.deluxe1.generic_tableview.sample

import com.deluxe1.generic_tableview.data.GenericPagedResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**Sample Paged response used for [com.deluxe1.generic_tableview.adapter.GenericPagedAdapter]*/
class SamplePagedResponse<T>(
    @SerializedName("page")
    @Expose
    override var page: Int,
    @SerializedName("pageCount")
    @Expose
    override var pageCount: Int,
    @SerializedName("data")
    @Expose
    override var data: Array<T>,
    @SerializedName("count")
    @Expose
    var count: Int = 0,
    @SerializedName("total")
    @Expose
    var total: Int = 0,
) : GenericPagedResponse<T>