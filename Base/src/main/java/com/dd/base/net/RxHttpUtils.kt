package com.dd.base.net

import com.dd.base.utils.log.LogUtils
import kotlinx.coroutines.flow.map
import rxhttp.asFlow
import rxhttp.toClass
import rxhttp.wrapper.param.RxHttp

object RxHttpUtils {

    const val SUCCESS = "200"

    suspend inline fun <reified T> getAwait(url: String, parameter: Map<String, Any> = mapOf()): T? {
        val data = RxHttp.get(url).addAll(parameter).toClass<BaseResponse<T>>().await()
        return if (data.code == SUCCESS && data.data != null) {
            data.data
        } else {
            LogUtils.d(data.msg)
            null
        }

    }

    suspend inline fun <reified T> postAwait(url: String, parameter: Map<String, Any> = mapOf()): T? {
        val data = RxHttp.postJson(url).addAll(parameter).toClass<BaseResponse<T>>().await()
        return if (data.code == SUCCESS && data.data != null) {
            data.data
        } else {
            LogUtils.d(data.msg)
            null
        }
    }

    suspend fun <T> getFlow(
        url: String,
        parameter: Map<String, Any> = mapOf(),
        onSuccess: (T?) -> Unit
    ) {
        RxHttp.get(url).addAll(parameter).toClass<BaseResponse<T>>().asFlow().map {
            if (it.code == SUCCESS && it.data != null) {
                it.data
            } else {
                LogUtils.d(it.msg)
                null
            }
        }.collect {
            onSuccess.invoke(it)
        }
    }

    suspend fun <T> postFlow(
        url: String,
        parameter: Map<String, Any> = mapOf(),
        onSuccess: (T?) -> Unit
    ) {
        RxHttp.postJson(url).addAll(parameter).toClass<BaseResponse<T>>().asFlow().map {
            if (it.code == SUCCESS && it.data != null) {
                it.data
            } else {
                LogUtils.d(it.msg)
                null
            }
        }.collect {
            onSuccess.invoke(it)
        }
    }
}