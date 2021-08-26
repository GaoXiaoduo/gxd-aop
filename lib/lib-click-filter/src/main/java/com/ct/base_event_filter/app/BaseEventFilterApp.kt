//package com.ct.base_event_filter.app
//
//import android.app.Application
//import android.content.Context
//import android.content.res.Configuration
//import android.util.Log
//import com.qlife.base_component.BuildConfig
//import com.qlife.lib_base.base_lib.app.appdelegate.IAppLife
//import com.qlife.lib_base.base_lib.app.appdelegate.PriorityLevel
//import ly.count.android.sdk.Countly
//import ly.count.android.sdk.CountlyConfig
//import ly.count.android.sdk.DeviceId
//
///**
// * BaseEventFilterApp,反射调用
// * @author gaoxiaoduo
// * @date 4/17/21 2:32 PM
// * @version 1.0
// */
//class BaseEventFilterApp : IAppLife {
//
//    override fun attachBaseContext(context: Context) {
//        Log.d(TAG, "attachBaseContext")
//
//    }
//
//    override fun onCreate(application: Application) {
//        Log.d(TAG, "onCreate")
//        app = application
//        initCountly()
//    }
//
//    override fun onTerminate(application: Application) {
//        Log.d(TAG, "onTerminate")
//
//    }
//
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        Log.d(TAG, "onConfigurationChanged")
//    }
//
//    override fun onLowMemory() {
//        Log.d(TAG, "onLowMemory")
//    }
//
//    override fun onTrimMemory(level: Int) {
//        Log.d(TAG, "onTrimMemory")
//    }
//
//    /**
//     * 设置该appLife的优先级，必须设置，否则不会回调
//     */
//    override fun onPriority(): String {
//        return PriorityLevel.MEDIUM
//    }
//
//    private fun initCountly() {
//        Log.d(TAG, "--initCountly--start---")
//
////        val config = CountlyConfig(
////            app,
////           "54aac084accd72787e4cc1528b14e7ad34c31495",
////            "https://logs.o3cloud.cn"
////        )
//        val config = CountlyConfig(
//            app,
//            BuildConfig.COUNTLY_APP_KEY,
//            BuildConfig.COUNTLY_SERVER_URL
//        )
//        if (BuildConfig.DEBUG) {
//            config.setLoggingEnabled(true)
//        }
//        config.setIdMode(DeviceId.Type.OPEN_UDID);
//        Countly.sharedInstance().init(config)
//        Countly.sharedInstance().setViewTracking(true)
//        Log.d(TAG, "--initCountly--end---")
//    }
//
//    companion object {
//        private const val TAG = "BaseEventFilterApp"
//        private lateinit var app: Application
//
//        fun getApp(): Application {
//            return app
//        }
//    }
//}
