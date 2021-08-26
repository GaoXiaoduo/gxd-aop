package com.ct.base_event_filter.bean

/**
 * countly上传实体类
 *
 * @author gaoxiaoduo
 * @date 4/16/21 11:05 AM
 * @version 1.0
 */
data class EventModule(

    /** 统计事件名称（中文）*/
    var key: String,
    /** 统计事件名称（英文）*/
    var keyEnglish: String,

    var count: Int = 0,

    var sum: Double = 0.0,

    /** 统计参数（中文）*/
    var segmentation: HashMap<String, Any> = HashMap<String, Any>(),

    /** 统计参数（英文）*/
    var segmentationEnglish: HashMap<String, Any> = HashMap<String, Any>()
)