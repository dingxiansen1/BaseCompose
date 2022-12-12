package com.dd.base.paging

class BaseResponse<T> {
    var code: Int = 0
    var msg: String? = null
    var data: T? = null
}