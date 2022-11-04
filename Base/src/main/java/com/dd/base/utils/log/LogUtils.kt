package com.dd.base.utils.log

import android.util.Log
import com.dd.base.BuildConfig
import com.dd.base.utils.log.LogUtils.Type.*
import com.dd.base.utils.log.LogUtils.enabled
import com.dd.base.utils.log.LogUtils.logHooks
import com.dd.base.utils.log.LogUtils.tag
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import kotlin.math.min

/**
 * @property tag 默认日志标签
 * @property enabled 日志全局开关
 * @property logHooks 日志拦截器
 */
object LogUtils {

    enum class Type {
        VERBOSE, DEBUG, INFO, WARN, ERROR, WTF
    }

    private const val TOP_CORNER = "┌────────────────────────────────────────────────────────"
    private const val MIDDLE_CORNER = "├────────────────────────────────────────────────────────"
    private const val LEFT_BORDER = "│ "
    private const val BOTTOM_CORNER = "└────────────────────────────────────────────────────────"

    /** 日志默认标签 */
    var tag = "日志"

    /** 是否启用日志 */
    var enabled = true

    /** 日志是否显示代码位置 */
    var traceEnabled = true

    /** 日志的Hook钩子 */
    val logHooks by lazy { ArrayList<LogHook>() }
    var scope : CoroutineScope?=null
    /**
     *开始日志
     **/
    fun init(enabled: Boolean = BuildConfig.DEBUG, tag: String = this.tag){
        scope = CoroutineScope(Dispatchers.IO)
        setDebug(enabled)
    }
    /**
     * 关闭日志
     **/
    fun cancel(){
        scope?.cancel()
        scope = null
    }
    /**
     * @param enabled 是否启用日志
     * @param tag 日志默认标签
     */
    private fun setDebug(enabled: Boolean, tag: String = this.tag) {
        this.enabled = enabled
        this.tag = tag
    }

    //<editor-fold desc="Hook">
    /**
     * 添加日志拦截器
     */
    fun addHook(hook: LogHook) {
        logHooks.add(hook)
    }

    /**
     * 删除日志拦截器
     */
    fun removeHook(hook: LogHook) {
        logHooks.remove(hook)
    }
    //</editor-fold>

    // <editor-fold desc="Log">

    @JvmOverloads
    @JvmStatic
    fun v(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {

        print(VERBOSE, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun i(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(INFO, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun d(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(DEBUG, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun w(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(WARN, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun e(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(ERROR, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun e(
        tr: Throwable? = null,
        tag: String = this.tag,
        occurred: Throwable? = Exception(),
        msg: Any? = "",
    ) {
        print(ERROR, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun wtf(
        msg: Any?,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(WTF, msg, tag, tr, occurred)
    }

    /**
     * 输出日志
     * 如果[msg]和[occurred]为空或者[tag]为空将不会输出日志, 拦截器
     *
     * @param type 日志等级
     * @param msg 日志信息
     * @param tag 日志标签
     * @param occurred 日志异常
     */
    private fun print(
        type: Type = INFO,
        msg: Any? = null,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        scope?.launch {
            Mutex().withLock {
                if (!enabled || msg == null) return@launch
                var message = msg.toString()
                val info = LogInfo(type, message, tag, tr, occurred)
                for (logHook in logHooks) {
                    logHook.hook(info)
                    if (info.msg == null) return@launch
                }

                if (traceEnabled && occurred != null) {
                    occurred.stackTrace.getOrNull(1)?.run {
                        log(type, TOP_CORNER, tag, tr)
                        log(type, "$LEFT_BORDER $className ($fileName:$lineNumber)", tag, tr)
                        log(type, MIDDLE_CORNER, tag, tr)
                    }
                }
                val max = 3800
                val length = message.length
                if (length > max) {
                    synchronized(this) {
                        var startIndex = 0
                        var endIndex = max
                        while (startIndex < length) {
                            endIndex = min(length, endIndex)
                            val substring = message.substring(startIndex, endIndex)
                            log(type, LEFT_BORDER + substring, tag, tr)
                            startIndex += max
                            endIndex += max
                        }
                    }
                } else {
                    log(type, LEFT_BORDER + message, tag, tr)
                }
                log(type, BOTTOM_CORNER, tag, tr)
            }
        }
    }

    /**
     * JSON格式化输出日志
     * @param tag 日志标签
     * @param msg 日志信息
     * @param type 日志类型
     * @param occurred 日志发生位置
     */
    @JvmOverloads
    @JvmStatic
    fun json(
        json: Any?,
        tag: String = this.tag,
        msg: String = "",
        type: Type = INFO,
        occurred: Throwable? = Exception()
    ) {
        if (!enabled || json == null) return

        var message = json.toString()

        val occurredMsg = if (traceEnabled && occurred != null) {
            occurred.stackTrace.getOrNull(1)?.run { " ($fileName:$lineNumber)" }
        } else ""

        if (message.isBlank()) {
            print(type, "$msg$occurredMsg\n$message", tag, occurred = null)
            return
        }

        val tokener = JSONTokener(message)
        val obj = try {
            tokener.nextValue()
        } catch (e: Exception) {
            "Parse json error"
        }

        message = when (obj) {
            is JSONObject -> obj.toString(2)
            is JSONArray -> obj.toString(2)
            else -> obj.toString()
        }

        print(type, "$msg$occurredMsg\n$message", tag, occurred = null)
    }

    private fun log(type: Type, msg: String, tag: String, tr: Throwable?) {
        when (type) {
            VERBOSE -> Log.v(tag,  msg, tr)
            DEBUG -> Log.d(tag,  msg, tr)
            INFO -> Log.i(tag,  msg, tr)
            WARN -> Log.w(tag, msg, tr)
            ERROR -> Log.e(tag,  msg, tr)
            WTF -> Log.wtf(tag,  msg, tr)
        }
    }
    // </editor-fold>
}