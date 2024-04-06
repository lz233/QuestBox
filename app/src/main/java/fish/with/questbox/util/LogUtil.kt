package fish.with.questbox.util

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import fish.with.questbox.App
import fish.with.questbox.App.Companion.TAG
import android.util.Log as ALog

object LogUtil {

    private const val maxLength = 4000
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    fun toast(msg: String) {
        handler.post {
            Toast.makeText(App.context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    private fun doLog(f: (String, String) -> Int, tag: String = TAG, obj: Any?, toToast: Boolean = false) {
        val str = if (obj is Throwable) ALog.getStackTraceString(obj) else obj.toString()
        if (str.length > maxLength) {
            val chunkCount: Int = str.length / maxLength
            for (i in 0..chunkCount) {
                val max: Int = 4000 * (i + 1)
                if (max >= str.length) {
                    doLog(f, tag = tag, obj = str.substring(maxLength * i), toToast = toToast)
                } else {
                    doLog(f, tag = tag, obj = str.substring(maxLength * i, max), toToast = toToast)
                }
            }
        } else {
            f(tag, str)
            if (toToast) toast(str)
        }
    }

    @JvmStatic
    fun _d(obj: Any?, tag: String = TAG) {
        doLog(ALog::d, tag = tag, obj = obj)
    }

    @JvmStatic
    @JvmOverloads
    fun d(obj: Any?, tag: String = TAG) {
        doLog(ALog::d, tag = tag, obj = obj)
    }

    @JvmStatic
    @JvmOverloads
    fun i(obj: Any?, tag: String = TAG) {
        doLog(ALog::i, tag = tag, obj = obj)
    }

    @JvmStatic
    @JvmOverloads
    fun e(obj: Any?, tag: String = TAG) {
        doLog(ALog::e, tag = tag, obj = obj)
    }

    @JvmStatic
    @JvmOverloads
    fun v(obj: Any?, tag: String = TAG) {
        doLog(ALog::v, tag = tag, obj = obj)
    }

    @JvmStatic
    @JvmOverloads
    fun w(obj: Any?, tag: String = TAG) {
        doLog(ALog::w, tag = tag, obj = obj)
    }
}