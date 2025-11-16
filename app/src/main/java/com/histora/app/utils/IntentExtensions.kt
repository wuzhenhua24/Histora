package com.histora.app.utils

import android.content.Intent
import android.os.Build
import java.io.Serializable

/**
 * 兼容新旧 API 的 Intent 扩展函数
 */
inline fun <reified T : Serializable> Intent.getSerializableExtraCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getSerializableExtra(key) as? T
    }
}
