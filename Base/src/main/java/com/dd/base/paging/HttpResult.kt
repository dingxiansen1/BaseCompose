package com.dd.base.paging

import java.lang.Exception

sealed class HttpResult<out T> {

    data class Success<T>(val result: T): HttpResult<T>()
    data class Error(val exception: Exception): HttpResult<Nothing>()

}
data class ListWrapper<T>(
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int,
    var datas: ArrayList<T>
)