package com.deluxe1.generic_tableview.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.deluxe1.generic_tableview.GenericListElement

open class GenericDataSource<T : GenericListElement>(val endpoint: GenericEndpoint<T>, val pageSize : Int= 10, val onlyPageForward : Boolean = true)
    : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 1
            Log.i("LOAD_ELEMENTS", nextPage.toString())
            val response = endpoint.getPagedResponse(nextPage, pageSize)

            val list = arrayListOf<T>()
            response.data.forEach {
                list.add(it)
            }

            return LoadResult.Page(
                data = list,
                prevKey = null,
                nextKey = if (response.pageCount == response.page) null else nextPage +1
            )
        } catch (e: Exception) {
            Log.i("LOAD_ELEMENTS", "ERROR" )
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }

    /**
     * Provide a [Key] used for the initial [load] for the next [PagingSource] due to invalidation
     * of this [PagingSource]. The [Key] is provided to [load] via [LoadParams.key].
     *
     * The [Key] returned by this method should cause [load] to load enough items to
     * fill the viewport around the last accessed position, allowing the next generation to
     * transparently animate in. The last accessed position can be retrieved via
     * [state.anchorPosition][PagingState.anchorPosition], which is typically
     * the top-most or bottom-most item in the viewport due to access being triggered by binding
     * items as they scroll into view.
     *
     * For example, if items are loaded based on integer position keys, you can return
     * [state.anchorPosition][PagingState.anchorPosition].
     *
     * Alternately, if items contain a key used to load, get the key from the item in the page at
     * index [state.anchorPosition][PagingState.anchorPosition].
     *
     * @param state [PagingState] of the currently fetched data, which includes the most recently
     * accessed position in the list via [PagingState.anchorPosition].
     *
     * @return [Key] passed to [load] after invalidation used for initial load of the next
     * generation. The [Key] returned by [getRefreshKey] should load pages centered around
     * user's current viewport. If the correct [Key] cannot be determined, `null` can be returned
     * to allow [load] decide what default key to use.
     */
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}