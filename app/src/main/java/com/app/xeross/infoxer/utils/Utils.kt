package com.app.xeross.infoxer.utils

import android.content.Context
import android.util.TypedValue

class Utils {
    companion object {
        fun dpToPx(dp: Int, context: Context) = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics))
    }
}