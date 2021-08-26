package com.ct.base_event_filter

import android.util.Log
import android.view.View
import com.ct.base_event_filter.constant.EventFilterConstants
import com.ct.base_event_filter.target.EventPathClick
import com.ct.base_event_filter.target.EventTypeClick
import com.ct.base_event_filter.utils.DoubleClickUtils
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature

/**
 * 点击事件拦截
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 2019-12-24 14:41
 */
@Aspect
class ClickFilter {
    /**
     * 是否为双击
     */
    private var isDoubleClick = false

    /**
     * 拦截双击
     */
    @Before("execution(@com.ct.base_event_filter.target.DoubleClick  * *(..))")
    @Throws(Throwable::class)
    fun beforeDoubleClickEnable(joinPoint: JoinPoint?) {
        Log.e("ClickFilterHook", "beforeDoubleClickEnable: ")
        isDoubleClick = true
    }

    /**
     * 过滤点击事件
     */
    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    @Throws(Throwable::class)
    fun clickFilter(joinPoint: ProceedingJoinPoint) {
        Log.e("ClickFilterHook", "-------------------------------------------")
        Log.d("ClickFilterHook", "clickFilter: ")
        // view 名称
        val args = joinPoint.args
        var view: View? = null
        for (arg in args) {
            if (arg is View) {
                view = arg as View
            }
        }
        //获取View 的 string id
        var resEntryName: String = ""
        try {
            if (view != null) {
                resEntryName = view.context.resources.getResourceEntryName(view.id)
            }
        } catch (e: Exception) {
            Log.d(TAG, "clickFilter resEntryName exception:${e.localizedMessage}")
        }

        val sig = joinPoint.signature
        val btnKey = "${sig.declaringTypeName}:${resEntryName}"
        postEvent(btnKey)

        /* 过滤双击 */
        if (isDoubleClick || !DoubleClickUtils.isDoubleClick) {
            joinPoint.proceed()
            isDoubleClick = false
        }
    }

    /**
     * 过滤事件类型
     */
    @Before("execution(@com.ct.base_event_filter.target.EventTypeClick * *(..))")
    @Throws(Throwable::class)
    fun aroundMethod(joinPoint: JoinPoint) {
        Log.d("ClickFilterHook", "----------aroundMethod---------------------------------")
        //拿到方法的签名
        val methodSignature = joinPoint.signature as MethodSignature
        //功能名
        var behaviorTrace: EventTypeClick? = null
        var eventType: String? = ""
        try {
            behaviorTrace = methodSignature.method.getAnnotation(EventTypeClick::class.java)
            eventType = behaviorTrace?.eventType.toString()

        } catch (e: Exception) {
            Log.d(TAG, "clickFilter aroundMethod exception:${e.localizedMessage}")
        }

        Log.d(TAG, "joinPoint:${joinPoint.toShortString()}")
        Log.d(TAG, "declaringTypeName:${methodSignature.declaringTypeName}")
        Log.d(TAG, "aroundMethod eventType:$eventType;")

        val btnKey = "${methodSignature.declaringTypeName}:${eventType}"
        postEvent(btnKey)
    }

    /**
     * 过滤路径类型
     */
    @Before("execution(@com.ct.base_event_filter.target.EventPathClick * *(..))")
    @Throws(Throwable::class)
    fun aroundMethodPath(joinPoint: JoinPoint) {
        Log.d("ClickFilterHook", "----------aroundMethodPath----")
        //拿到方法的签名
        val methodSignature = joinPoint.signature as MethodSignature
        //功能名
        var behaviorTrace: EventPathClick? = null
        var eventPath: String? = ""
        try {
            behaviorTrace = methodSignature.method.getAnnotation(EventPathClick::class.java)
            eventPath = behaviorTrace?.eventPath.toString()

        } catch (e: Exception) {
            e.printStackTrace();
            Log.d(TAG, "clickFilter aroundMethodPath exception:${e.printStackTrace()}")
            Log.d(TAG, "clickFilter aroundMethodPath exception:${e.localizedMessage}")
        }
        //----
        //val signature: Signature = joinPoint.getSignature() //方法签名
        //val method: Method = (signature as MethodSignature).method
        //这个方法才是目标对象上有注解的方法
        //val realMethod: Method = joinPoint.target.javaClass
        //    .getDeclaredMethod(signature.getName(), method.getParameterTypes())
        //val authorizationNeed: EventPathClick = realMethod.getAnnotation(EventPathClick::class.java)

        //----
        Log.d(TAG, "joinPoint:${joinPoint.toShortString()}")
        Log.d(TAG, "declaringTypeName:${methodSignature.declaringTypeName}")
        Log.d(TAG, "aroundMethod eventPath:$eventPath;")

        val btnKey = "$eventPath"
        postEvent(btnKey)
    }

    private fun postEvent(btnKey: String) {
        Log.d(TAG, "postEvent btnKey:$btnKey;")
        val eventModule = EventFilterConstants.getEvent(btnKey) ?: return

        Log.d(TAG, "postEvent eventModule key:${eventModule.key};")
        Log.d(TAG, "postEvent eventModule key:${eventModule.keyEnglish};")

        // TODO: 4/17/21 by gaoxiaoduo  正式去掉：gxd_测试_
        /* countly事件埋点统计 */
//        Countly.sharedInstance().events().recordEvent(
//            "android_" + eventModule.key,
//            eventModule.segmentation,
//            eventModule.count,
//            eventModule.sum
//        )
//
//        /* 友盟事件埋点统计 */
//        if (eventModule.segmentationEnglish.isNullOrEmpty()) {
//            MobclickAgent.onEvent(
//                BaseEventFilterApp.getApp().applicationContext,
//                eventModule.keyEnglish
//            )
//        } else {
//            MobclickAgent.onEventObject(
//                BaseEventFilterApp.getApp().applicationContext,
//                eventModule.keyEnglish,
//                eventModule.segmentationEnglish
//            )
//        }
    }

    companion object {
        private const val TAG = "ClickFilter"
    }
}