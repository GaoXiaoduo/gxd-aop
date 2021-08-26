package com.ct.base_event_filter.constant

import android.text.TextUtils
import com.ct.base_event_filter.bean.EventModule

/**
 * 事件过滤常量
 * @author gaoxiaoduo
 * @date 4/17/21 2:16 PM
 * @version 1.0
 */
object EventFilterConstants {

    private val mHashMap = java.util.HashMap<String, EventModule>()

    object EventCode {

        /** 入口统计-房型 */
        const val EVENT_CODE_1: Int = 1001

        /** 入口统计-房态 */
        const val EVENT_CODE_2: Int = 1002

        /** 入口统计-房价 */
        const val EVENT_CODE_3: Int = 1003

        /** 入口统计-渠道直连 */
        const val EVENT_CODE_4: Int = 1004

        /** 入口统计-财务统计 */
        const val EVENT_CODE_5: Int = 1005

        /** 入口统计-门锁记录 */
        const val EVENT_CODE_6: Int = 1006

        /** 入口统计-消息 */
        const val EVENT_CODE_7: Int = 1007

    }

    object EventPath {

        /*------------------- 基础价格-渠道改价 ------------------------*/

        /** 榛果民宿（基础价格）*/
        const val EVENT_PATH_1: String = "path_1"

        /** 爱彼迎 （基础价格）*/
        const val EVENT_PATH_2: String = "path_2"

        /** 小猪短租（基础价格）*/
        const val EVENT_PATH_3: String = "path_3"

        /** 途家 （基础价格）*/
        const val EVENT_PATH_4: String = "path_4"

        /*------------------- 渠道直连 ------------------------*/

        /** 榛果民宿（渠道直连-授权）*/
        const val EVENT_PATH_5: String = "path_5"

        /** 爱彼迎 （渠道直连-授权）*/
        const val EVENT_PATH_6: String = "path_6"

        /** 小猪短租（渠道直连-授权）*/
        const val EVENT_PATH_7: String = "path_7"

        /** 途家 （渠道直连-授权）*/
        const val EVENT_PATH_8: String = "path_8"

        /** 渠道直连-账号删除 */
        const val EVENT_PATH_9: String = "path_9"

        /** 渠道直连-去关联 */
        const val EVENT_PATH_10: String = "path_10"

        /** 房源关联-去关联 */
        const val EVENT_PATH_11: String = "path_11"

        /** 房源管理-去关联 */
        const val EVENT_PATH_12: String = "path_12"

        /** 渠道直连-房源管理-解除关联 */
        const val EVENT_PATH_13: String = "path_13"


        /*------------------- 财务报表 ------------------------*/

        /** 财务统计-详情 */
        const val EVENT_PATH_14: String = "path_14"

        /** 财务统计-详情-收入 */
        const val EVENT_PATH_15: String = "path_15"

        /** 财务统计-详情-支出 */
        const val EVENT_PATH_16: String = "path_16"

        /** 财务统计-详情-统计分析 */
        const val EVENT_PATH_17: String = "path_17"

        /*------------------- 合同管理 ------------------------*/

        /** 合同管理-签署 */
        const val EVENT_PATH_18: String = "path_18"

        /*------------------- 账单收益 ------------------------*/

        /** 入口统计-确认账单 */
        const val EVENT_PATH_19: String = "path_19"

        /** 入口统计-账单详情 */
        const val EVENT_PATH_20: String = "path_20"

        /*------------------- 订单 ------------------------*/

        /** 入口统计-订单详情 */
        const val EVENT_PATH_21: String = "path_21"

        /** 订单详情-接受订单 */
        const val EVENT_PATH_22: String = "path_22"

        /** 订单详情-拒绝订单 */
        const val EVENT_PATH_23: String = "path_23"

        /** 入口统计-订单详情-房单详情 */
        const val EVENT_PATH_24: String = "path_24"

        /** 订单详情-房单详情-办理入住 */
        const val EVENT_PATH_25: String = "path_25"

        /** 订单详情-房单详情-调整房间 */
        const val EVENT_PATH_26: String = "path_26"

        /*------------------- 托管经营 ------------------------*/

        /** 入口统计-托管经营 */
        const val EVENT_PATH_27: String = "path_27"

        /** 托管经营-申请 */
        const val EVENT_PATH_28: String = "path_28"

        /** 托管经营-申请-提交 */
        const val EVENT_PATH_29: String = "path_29"

        /*------------------- 退出 ------------------------*/

        /** 系统统计-退出 */
        const val EVENT_PATH_30: String = "path_30"
    }

    init {

        /*------------------- 登录 ------------------------*/
        mHashMap["com.ct.biz_user.login.LoginActivity:btn_login"] =
            EventModule("系统统计-登录", "EVENT_SYSTEM_LOGIN", 1, 1.0)
        mHashMap[EventPath.EVENT_PATH_30] =
            EventModule("系统统计-退出", "EVENT_SYSTEM_LOGOUT", 1, 1.0)

        /*------------------- Home入口 ------------------------*/

        mHashMap["com.ct.biz_home.home.fragment.HomeFragment:${EventCode.EVENT_CODE_1}"] =
            EventModule("入口统计-房型", "EVENT_PRODUCT", 1, 1.0)

        mHashMap["com.ct.biz_home.home.fragment.HomeFragment:${EventCode.EVENT_CODE_2}"] =
            EventModule("入口统计-房态", "EVENT_PRODUCT_TYPE", 1, 1.0)

        mHashMap["com.ct.biz_home.home.fragment.HomeFragment:${EventCode.EVENT_CODE_3}"] =
            EventModule("入口统计-房价", "EVENT_PRODUCT_PRICE", 1, 1.0)

        mHashMap["com.ct.biz_home.home.fragment.HomeFragment:${EventCode.EVENT_CODE_4}"] =
            EventModule("入口统计-渠道直连", "EVENT_CHANNEL_DIRECT_CONNECTION", 1, 1.0)

        mHashMap["com.ct.biz_home.home.fragment.HomeFragment:${EventCode.EVENT_CODE_5}"] =
            EventModule("入口统计-财务统计", "EVENT_FINANCIAL_STATISTICS", 1, 1.0)

        mHashMap["com.ct.biz_home.home.fragment.HomeFragment:${EventCode.EVENT_CODE_6}"] =
            EventModule("入口统计-门锁记录", "EVENT_DOOR_RECORD", 1, 1.0)

        mHashMap["com.ct.biz_home.home.HomeActivity:${EventCode.EVENT_CODE_7}"] =
            EventModule("入口统计-消息", "EVENT_NEWS", 1, 1.0)

        mHashMap["com.ct.biz_product.product.ProductManagerActivity:btn_create_product"] =
            EventModule("入口统计-房型管理-创建", "EVENT_PRODUCT_MANAGEMENT_CREATE", 1, 1.0)

        mHashMap["com.ct.biz_product.product.CreateProductActivity:btn_save"] =
            EventModule("房型管理-创建房型-保存", "EVENT_PRODUCT_MANAGEMENT_SAVE", 1, 1.0)

        mHashMap["com.ct.biz_product.product.CreateProductActivity:btn_continue"] =
            EventModule("房型管理-创建房型-保存并继续", "EVENT_PRODUCT_MANAGEMENT_SAVE_CONTINUE", 1, 1.0)

        mHashMap["/product/price/base/set/activity"] =
            EventModule("房型管理-房型详情-基础价设置入口", "EVENT_PRODUCT_DETAIL_BASE_SET_ENTRY", 1, 1.0)

        /*------------------- 基础价格-渠道改价 ------------------------*/

        mHashMap[EventPath.EVENT_PATH_1] =
            EventModule(
                "房型管理-房型详情-单个渠道改价",
                "EVENT_PRODUCT_DETAIL_CHANNEL_MODIFY_PRICE",
                1,
                1.0,
                getChannelMeituanSegmentation(false),
                getChannelMeituanSegmentation(true)
            )

        mHashMap[EventPath.EVENT_PATH_2] =
            EventModule(
                "房型管理-房型详情-单个渠道改价",
                "EVENT_PRODUCT_DETAIL_CHANNEL_MODIFY_PRICE",
                1,
                1.0,
                getChannelAirbnbSegmentation(false),
                getChannelAirbnbSegmentation(true)
            )


        mHashMap[EventPath.EVENT_PATH_3] =
            EventModule(
                "房型管理-房型详情-单个渠道改价",
                "EVENT_PRODUCT_DETAIL_CHANNEL_MODIFY_PRICE",
                1,
                1.0,
                getChannelXiaozhuSegmentation(false),
                getChannelXiaozhuSegmentation(true)
            )

        mHashMap[EventPath.EVENT_PATH_4] = EventModule(
            "房型管理-房型详情-单个渠道改价",
            "EVENT_PRODUCT_DETAIL_CHANNEL_MODIFY_PRICE",
            1,
            1.0,
            getChannelTujiaSegmentation(false),
            getChannelTujiaSegmentation(true)
        )

        /*------------------- 渠道直连 ------------------------*/

        mHashMap[EventPath.EVENT_PATH_5] =
            EventModule(
                "渠道直连-添加授权", "EVENT_CHANNEL_ADD_AUTHORIZE", 1, 1.0,
                getChannelMeituanSegmentation(false),
                getChannelMeituanSegmentation(true)
            )

        mHashMap[EventPath.EVENT_PATH_6] =
            EventModule(
                "渠道直连-添加授权", "EVENT_CHANNEL_ADD_AUTHORIZE", 1, 1.0,
                getChannelAirbnbSegmentation(false),
                getChannelAirbnbSegmentation(true)
            )

        mHashMap[EventPath.EVENT_PATH_7] =
            EventModule(
                "渠道直连-添加授权", "EVENT_CHANNEL_ADD_AUTHORIZE", 1, 1.0,
                getChannelXiaozhuSegmentation(false),
                getChannelXiaozhuSegmentation(true)
            )

        mHashMap[EventPath.EVENT_PATH_8] =
            EventModule(
                "渠道直连-添加授权", "EVENT_CHANNEL_ADD_AUTHORIZE", 1, 1.0,
                getChannelTujiaSegmentation(false),
                getChannelTujiaSegmentation(true)
            )

        mHashMap[EventPath.EVENT_PATH_9] =
            EventModule("渠道直连-删除授权绑定", "EVENT_CHANNEL_DELETE_AUTHORIZE", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_10] =
            EventModule("渠道直连-去关联", "EVENT_CHANNEL_TO_ASSOCIATION", 1, 1.0)

        mHashMap["com.ct.biz_channel.channel.fragment.ChannelFragment:cl_sync"] =
            EventModule("渠道直连-同步房源", "EVENT_CHANNEL_SYNC_PRODUCT", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_11] =
            EventModule("渠道直连-房源关联-去关联", "EVENT_CHANNEL_HOUSE_ASSOCIATION_TO_ASSOCIATION", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_12] =
            EventModule("渠道直连-房源管理-去关联", "EVENT_CHANNEL_HOUSE_MANAGEMENT_TO_ASSOCIATION", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_13] =
            EventModule(
                "渠道直连-房源管理-解除关联",
                "EVENT_CHANNEL_HOUSE_MANAGEMENT_DELETE_ASSOCIATION",
                1,
                1.0
            )

        /*------------------- 财务统计 ------------------------*/

        mHashMap[EventPath.EVENT_PATH_14] =
            EventModule("财务统计-详情", "EVENT_FINANCIAL_STATISTICS_DETAIL", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_15] =
            EventModule("财务统计-详情-收入", "EVENT_FINANCIAL_STATISTICS_DETAIL_INCOME", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_16] =
            EventModule("财务统计-详情-支出", "EVENT_FINANCIAL_STATISTICS_DETAIL_SPENDING", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_17] =
            EventModule("财务统计-详情-统计分析", "EVENT_FINANCIAL_STATISTICS_DETAIL_ANALYSES", 1, 1.0)

        /*------------------- 合同管理 ------------------------*/

        mHashMap[EventPath.EVENT_PATH_18] =
            EventModule("合同管理-签署", "EVENT_CONTRACT_MANAGEMENT_SIGN", 1, 1.0)

        /*------------------- 账单收益 ------------------------*/

        mHashMap[EventPath.EVENT_PATH_19] =
            EventModule("入口统计-确认账单", "EVENT_BILL_CONFIRM_BILL", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_20] =
            EventModule("入口统计-账单详情", "EVENT_BILL_DETAIL", 1, 1.0)

        /*------------------- 订单 ------------------------*/

        mHashMap[EventPath.EVENT_PATH_21] =
            EventModule("入口统计-订单详情", "EVENT_ORDER_DETAIL", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_22] =
            EventModule("订单详情-接受订单", "EVENT_ORDER_DETAIL_ACCEPT_ORDER", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_23] =
            EventModule("订单详情-拒绝订单", "EVENT_ORDER_DETAIL_REFUSE_ORDER", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_24] =
            EventModule("入口统计-订单详情-房单详情", "EVENT_ORDER_DETAIL_HOUSE_DETAIL", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_25] =
            EventModule("订单详情-房单详情-办理入住", "EVENT_ORDER_DETAIL_HOUSE_DETAIL_MARK_CHECKIN", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_26] =
            EventModule("订单详情-房单详情-调整房间", "EVENT_ORDER_DETAIL_HOUSE_DETAIL_ADJUST_HOUSE", 1, 1.0)

        /*------------------- 托管经营 ------------------------*/

        mHashMap[EventPath.EVENT_PATH_27] =
            EventModule("入口统计-托管经营", "EVENT_CUSTODY_OPERATION", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_28] =
            EventModule("托管经营-申请", "EVENT_CUSTODY_OPERATION_APPLY", 1, 1.0)

        mHashMap[EventPath.EVENT_PATH_29] =
            EventModule("托管经营-申请-提交", "EVENT_CUSTODY_OPERATION_APPLY_SUBMIT", 1, 1.0)
    }

    private fun getChannelMeituanSegmentation(isKeyEnglish: Boolean): HashMap<String, Any> {
        val segmentation: HashMap<String, Any> = HashMap<String, Any>()
        if (isKeyEnglish) {
            segmentation["meituan"] = "榛果民宿"
        } else {
            segmentation["渠道"] = "榛果民宿"
        }
        return segmentation
    }

    private fun getChannelXiaozhuSegmentation(isKeyEnglish: Boolean): HashMap<String, Any> {
        val segmentation: HashMap<String, Any> = HashMap<String, Any>()
        if (isKeyEnglish) {
            segmentation["xiaozhu"] = "小猪短租"
        } else {
            segmentation["渠道"] = "小猪短租"
        }
        return segmentation
    }

    private fun getChannelAirbnbSegmentation(isKeyEnglish: Boolean): HashMap<String, Any> {
        val segmentation: HashMap<String, Any> = HashMap<String, Any>()
        if (isKeyEnglish) {
            segmentation["airbnb"] = "爱彼迎"
        } else {
            segmentation["渠道"] = "爱彼迎"
        }
        return segmentation
    }

    private fun getChannelTujiaSegmentation(isKeyEnglish: Boolean): HashMap<String, Any> {
        val segmentation: HashMap<String, Any> = HashMap<String, Any>()
        if (isKeyEnglish) {
            segmentation["tujia"] = "途家"
        } else {
            segmentation["渠道"] = "途家"
        }
        return segmentation
    }


    fun getEvent(btnKey: String): EventModule? {
        if (TextUtils.isEmpty(btnKey)) {
            return null
        }
        if (!mHashMap.containsKey(btnKey)) {
            return null
        }
        return mHashMap[btnKey]
    }
}