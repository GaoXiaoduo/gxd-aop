package com.ct.base_event_filter.utils

/**
 * 双击工具类
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 2019-12-24 15:53
 */
object DoubleClickUtils {
    /**
     * 2次点击的间隔时间，单位ms
     */
    private const val SPACE_TIME = 500L
    private var lastClickTime: Long = 0

    /**
     * 是否为双击
     *
     * @return true:是双击,false:不是
     */
    @get:Synchronized
    val isDoubleClick: Boolean
        get() {
            val currentTime = System.currentTimeMillis()
            val isDoubleClick: Boolean
            isDoubleClick = currentTime - lastClickTime <= SPACE_TIME
            lastClickTime = currentTime
            return isDoubleClick
        }
}