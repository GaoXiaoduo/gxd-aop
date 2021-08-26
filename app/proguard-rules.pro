# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


##############################################
##################  日历范围选择混淆  ###########
##############################################
# 依赖：com.haibin:calendarview:3.7.0
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}
#组件化
# 保留app-dispatcher库
-keep class com.qlife.lib_base.base_lib.app.**{*;}
# 保留实现了IAppLife 的所有 appLife类
-keep class * implements com.qlife.lib_base.base_lib.app.appdelegate.IAppLife

##############################################
##################  混淆基本配置信息  ###########
##############################################
# 代码混淆压缩比,在0-7之间,默认为5,一般不需要改
-optimizationpasses 5
# 混淆时不使用大小写混合,混淆后的类名为小写
-dontusemixedcaseclassnames
# 不跳过library中的非public的类
# 指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses
# 指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers
# 不进行优化，建议使用此选项，因为根据proguard-android-optimize.txt中的描述，
# 优化可能会造成一些潜在风险，不能保证在所有版本的Dalvik上都正常运行
# -dontoptimize
# 不做预校验,preverify是proguard的4个步骤之一
# Android不需要preverify,去掉这一步可加快混淆速度
-dontpreverify
# 指定混淆是采用的算法,后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法,一般不改变
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
# 保护代码中的Annotation不被混淆
# 这在JSON实体映射时非常重要,比如fastJson,Gson
-keepattributes *Annotation*
# 避免混淆泛型
# 这在JSO实体映射时非常重要,比如fastJson,Gson
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
-keepattributes InnerClasses

##############################################
##################  是否记录日志文件  ###########
##############################################
# 混淆后就会生成映射文件
# 打印混淆的详细信息
-verbose
# apk 包内所有 class 的内部结构
# -dump class_files.txt
# 未混淆的类和成员
# -printseeds seeds.txt
# 列出从 apk 中删除的代码
-printusage unused.txt
# 混淆前后的映射
# 包含有类名 -> 混淆后类名的映射关系
-printmapping proguard_mapping.txt
#为了保留所需的信息，以便 Crashlytics 生成人能阅读的崩溃报告，请在您的 Proguard 或 Dexguard 配置文件中添加以下行：
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
#要让 Crashlytics 自动上传 ProGuard 或 DexGuard 映射文件，请从配置文件中移除以下行（若存在）：
#-printmapping mapping.txt
##############################################
##################  需要保留的信息  #############
##############################################
# 保留所有的本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
# 保留了继承自Activity,Application这些类的子类
# 因为这些子类都有可能被外部调用
# 比如说,第一行就保证了所有Activity的子类不要被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
# 保留support下的所有类及其内部类
-keep class android.support.** {*;}
# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**
-keep public class * extends adnroid.app.Fragment
# AndroidX 防止混淆
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}
# 保留在Activity中的方法参数时view的方法
# 从而我们在layout里面编写onClick就不会被影响
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}
# 枚举类不能被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# 保留自定义控件(继承自View)不被混淆
-keep public class * extends android.view.View {
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# 保留Parcelable序列化的类不被混淆
-keep class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements android.os.Parcelable {
  static ** CREATOR;
}
# 保留Serializable序列化的类不被混淆
-keep public class * implements java.io.Serializable {*;}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# 对于R(资源)下的所有类及其方法,都不能被混淆
-keep class **.R$* { *; }
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}
# 保留实体类和成员不被混淆
#-keep class com.quhuo.boss.bean.** {
#    *;
#}
# 保留实体类和成员不被混淆
-keep class com.qlife.base_component.** {
    *;
}
#保留网络请求
#-keep class com.quhuo.boss.net.**{
#    *;
#}
# 保留网络请求
-keep class com.qlife.base_component.net.** {
    *;
}
-keep class com.ct.biz_channel.bean.** {
    *;
}

# 保留实体类和成员不被混淆
#-keep class com.qlife.biz_contact.bean.** {
#    *;
#}
# 保留实体类和成员不被混淆
-keep class com.ct.base_component_shopkeeper.bean.**{
    *;
}
# 保留实体类和成员不被混淆
-keep class com.quhuo.lib.linkage.** { *; }
-dontwarn com.quhuo.lib.linkage.**

# 保留实体类和成员不被混淆
-keep class com.quhuo.sort.** { *; }
-dontwarn com.quhuo.sort.**

# 保留实体类和成员不被混淆
-keep class com.quhuo.lib.update.**{
     *;
}
 # 保留实体类和成员不被混淆
-keep class com.okeyun.util.**{
     *;
}
# 保留实体类和成员不被混淆
-keep class com.okeyun.adapter.**{
      *;
}
# 保留实体类和成员不被混淆
-keep class com.qlife.code.**{
      *;
}
# SwipeToLoadLayout

-dontwarn com.aspsine.swipetoloadlayout.**
-keep class com.aspsine.swipetoloadlayout.** { *;}

# SwipeToLoadLayout
-dontwarn com.github.gcacace.signaturepad.**
-keep class com.github.gcacace.signaturepad.** { *;}
##############################################
##################  组件化相关  ################
##############################################
# 保留app声明周期分发类不被混淆
-keep class * implements com.qlife.lib_base.base_lib.app.appdelegate.IAppLife
##############################################
##################  Butter Knife  ############
##############################################
# Retain generated class which implement Unbinder.
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }

# Prevent obfuscation of types which use ButterKnife annotations since the simple name
# is used to reflectively look up the generated ViewBinding.
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

# Gson混淆
-dontwarn com.google.gson.**
-keep class com.google.gson.** {*;}
-keep class sun.misc.Unsafe { *; }

# Retrofit 2.x
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# rxjava 1.x
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

##############################################
##################  EventBus  ################
##############################################
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

##############################################
##################  Glide  ###################
##############################################
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# for DexGuard only 付费代码混淆软件
# -keepresourcexmlelements manifest/application/meta-data@value=GlideModule

# easypermissions
-keepclassmembers class * {
    @pub.devrel.easypermissions.AfterPermissionGranted <methods>;
}
# stetho
-keep class com.facebook.stetho.** { *; }
-dontwarn com.facebook.stetho.**

# frabic crashlytics
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

# logger
-keep class com.orhanobut.logger.** { *; }
-dontwarn com.orhanobut.logger.**

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# rxandroidble
-keep class com.polidea.rxandroidble.** { *;}
-dontwarn com.polidea.rxandroidble.**

#OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

#OkHttp logging-interceptor
-dontwarn okhttp3.logging.**
-keep class okhttp3.logging.** { *;}

#############################################
##################  七牛相关  #################
##############################################
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}
#############################################＃
##################  arouter   ################
##############################################
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider
# If @Autowired is used for injection in non-Activity classes, add the following rules to prevent injection failures
# -keepnames class * {
#    @com.alibaba.android.arouter.facade.annotation.Autowired <fields>;
#}

##############################################
################## TbsReaderView pdf #########
##############################################
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-dontoptimize
-dontusemixedcaseclassnames
-verbose
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontwarn dalvik.**
-dontwarn com.tencent.smtt.**
#-optimizationpasses 7

#-overloadaggressively

# ------------------ Keep LineNumbers and properties ---------------- #
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
# --------------------------------------------------------------------------

# Addidional for x5.sdk classes for apps
-keep class com.tencent.smtt.export.external.**{
    *;
}

-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
	*;
}

-keep class com.tencent.smtt.sdk.CacheManager {
	public *;
}

-keep class com.tencent.smtt.sdk.CookieManager {
	public *;
}

-keep class com.tencent.smtt.sdk.WebHistoryItem {
	public *;
}

-keep class com.tencent.smtt.sdk.WebViewDatabase {
	public *;
}

-keep class com.tencent.smtt.sdk.WebBackForwardList {
	public *;
}

-keep public class com.tencent.smtt.sdk.WebView {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
	public static final <fields>;
	public java.lang.String getExtra();
	public int getType();
}

-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebView$PictureListener {
	public <fields>;
	public <methods>;
}


-keepattributes InnerClasses

-keep public enum com.tencent.smtt.sdk.WebSettings$** {
    *;
}

-keep public enum com.tencent.smtt.sdk.QbSdk$** {
    *;
}

-keep public class com.tencent.smtt.sdk.WebSettings {
    public *;
}


-keepattributes Signature
-keep public class com.tencent.smtt.sdk.ValueCallback {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebViewClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.DownloadListener {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebChromeClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
	public <fields>;
	public <methods>;
}

-keep class com.tencent.smtt.sdk.SystemWebChromeClient{
	public *;
}
# 1. extension interfaces should be apparent
-keep public class com.tencent.smtt.export.external.extension.interfaces.* {
	public protected *;
}

# 2. interfaces should be apparent
-keep public class com.tencent.smtt.export.external.interfaces.* {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebIconDatabase {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebStorage {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.DownloadListener {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.Tbs* {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.LogFileUtils {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.TbsLog {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.TbsLogClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.CookieSyncManager {
	public <fields>;
	public <methods>;
}

# Added for game demos
-keep public class com.tencent.smtt.sdk.TBSGamePlayer {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.Apn {
	public <fields>;
	public <methods>;
}
-keep class com.tencent.smtt.** {
	*;
}
# end

-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
	public <fields>;
	public <methods>;
}

-keep class MTT.ThirdAppInfoNew {
	*;
}

-keep class com.tencent.mtt.MttTraceEvent {
	*;
}

# Game related
-keep public class com.tencent.smtt.gamesdk.* {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.TBSGameBooter {
        public <fields>;
        public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {
	public protected *;
}

-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {
	public *;
}
#------------------------------------------------------------------------
#------------------  下方是共性的排除项目         ---------------------------
# 方法名中含有“JNI”字符的，认定是Java Native Interface方法，自动排除
# 方法名中含有“JRI”字符的，认定是Java Reflection Interface方法，自动排除

-keepclasseswithmembers class * {
    ... *JNI*(...);
}

-keepclasseswithmembernames class * {
	... *JRI*(...);
}

-keep class **JNI* {*;}

#------------------------------------------------------------------------
#------------------  AndroidPicker滚轮选择器    ---------------------------

-keepattributes InnerClasses,Signature
-keepattributes *Annotation*

-keep class cn.qqtheme.framework.entity.** { *;}
#---------------------------------------------------------------------------
#------------------  greenDAO   --------------------------------------------
### greenDAO 3
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use RxJava:
-dontwarn rx.**

### greenDAO 2
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties


#---------------------------------------------------------------------------
#------------------  LinkedMe   --------------------------------------------
-keep class com.microquation.linkedme.android.** { *; }

#---------------------------------------------------------------------------
#------------------  immersionbar   ----------------------------------------
-keep class com.gyf.immersionbar.* {*;}
 -dontwarn com.gyf.immersionbar.**

#---------------------------------------------------------------------------
#------------------  hujiang aspectj   -------------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
#-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-dontshrink
-dontoptimize

#不混淆使用了 annotation的类
-keepattributes *Annotation*
#不混淆javascript
-keepattributes JavascriptInterface
#不混淆 使用反射机制的类
-keepattributes Signature
#忽略警告
-ignorewarnings

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
##---------------End: proguard configuration for Gson  ----------

##---------------------------Begin: cdn  --------------------------
-keep class com.mato.** { *; }
-dontwarn com.mato.**
-keepattributes Exceptions, Signature, InnerClasses
##---------------------------End: cdn  --------------------------

##---------------Begin: proguard configuration for share------------
-dontwarn org.apache.commons.**
-keep public class com.weibo.sdk.android.**{  *; }
-keep public class com.sina.sso.**{*;}
-keep public class android.net.http.SslError{*;}
-keep public class com.tencent.**{*;}
-keep class com.hujiang.loginmodule.api.model.** { *; }
-keep class com.hujiang.social.sdk.model.** { *; }
##---------------End: proguard configuration for share--------------

-keep class com.hujiang.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-dontwarn com.umeng.**
-dontwarn org.apache.commons.**
-dontwarn com.tencent.weibo.sdk.**
-keepattributes *Annotation*

-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }

#GSYVideoPlayer
 -keep class com.shuyu.gsyvideoplayer.video.** { *; }
 -dontwarn com.shuyu.gsyvideoplayer.video.**
 -keep class com.shuyu.gsyvideoplayer.video.base.** { *; }
 -dontwarn com.shuyu.gsyvideoplayer.video.base.**
 -keep class com.shuyu.gsyvideoplayer.utils.** { *; }
 -dontwarn com.shuyu.gsyvideoplayer.utils.**
 -keep class tv.danmaku.ijk.** { *; }
 -dontwarn tv.danmaku.ijk.**

 -keep public class * extends android.view.View{
     *** get*();
     void set*(***);
     public <init>(android.content.Context);
     public <init>(android.content.Context, android.util.AttributeSet);
     public <init>(android.content.Context, android.util.AttributeSet, int);
 }


# 输出 R8 在构建项目时应用的所有规则的完整报告
# -printconfiguration ~/tmp/full-r8-config.txt
# 百度
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

#---------------------------------------------------------------------------
#------------------  阿里云 一键登录  -----------------------------------------

#-keep class cn.com.chinatelecom.gateway.lib.** {*;}
#-keep class com.unicom.xiaowo.login.** {*;}
#-keep class com.cmic.sso.sdk.** {*;}
#-keep class com.mobile.auth.** {*;}
#-keep class android.support.v4.** { *;}
#-keep class org.json.**{*;}
#-keep class com.alibaba.fastjson.** {*;}
#
#
##CacheWebview
#-dontwarn ren.yale.android.cachewebviewlib.**
#-keep class ren.yale.android.cachewebviewlib.**{*;}
#
##okhttp
#-dontwarn okhttp3.**
#-keep class okhttp3.**{*;}
#
##okio
#-dontwarn okio.**
#-keep class okio.**{*;}


##############################################
##################  kotlin  ##################
##############################################
-dontwarn kotlin.reflect.jvm.internal.**
-keep class kotlin.reflect.jvm.internal.** { *; }


##############################################
##################  阿里推送  ##################
##############################################
#-keepclasseswithmembernames class ** {
#    native <methods>;
#}
#-keepattributes Signature
#-keep class sun.misc.Unsafe { *; }
#-keep class com.taobao.** {*;}
#-keep class com.alibaba.** {*;}
#-keep class com.alipay.** {*;}
#-keep class com.ut.** {*;}
#-keep class com.ta.** {*;}
#-keep class anet.**{*;}
#-keep class anetwork.**{*;}
#-keep class org.android.spdy.**{*;}
#-keep class org.android.agoo.**{*;}
#-keep class android.os.**{*;}
#-keep class org.json.**{*;}
#-dontwarn com.taobao.**
#-dontwarn com.alibaba.**
#-dontwarn com.alipay.**
#-dontwarn anet.**
#-dontwarn org.android.spdy.**
#-dontwarn org.android.agoo.**
#-dontwarn anetwork.**
#-dontwarn com.ut.**
#-dontwarn com.ta.**
##如果集成推送SDK的工程开启代码混淆，在Proguard配置的基础上，需要添加以下辅助通道的Proguard配置。
## 小米通道
#-keep class com.xiaomi.** {*;}
#-dontwarn com.xiaomi.**
## 华为通道
#-keep class com.huawei.** {*;}
#-dontwarn com.huawei.**
## GCM/FCM通道
#-keep class com.google.firebase.**{*;}
#-dontwarn com.google.firebase.**
## OPPO通道
#-keep public class * extends android.app.Service
## VIVO通道
#-keep class com.vivo.** {*;}
#-dontwarn com.vivo.**
## 魅族通道
#-keep class com.meizu.cloud.** {*;}
#-dontwarn com.meizu.cloud.**

##############################################
##################  baidu ocr ui  ############
##############################################
-keep class com.baidu.ocr.sdk.**{*;}
-dontwarn com.baidu.ocr.**

#---------------------------------------------------------------------------
#------------------  友盟 start  --------------------------------------------
#友盟基础组件
# 友盟统计混淆---- start -----
-keep class com.umeng.** {*;}

-keep class com.uc.** {*;}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class com.zui.** {*;}
-keep class com.miui.** {*;}
-keep class com.heytap.** {*;}
-keep class a.** {*;}
-keep class com.vivo.** {*;}

#SDK需要引用导入工程的资源文件，通过了反射机制得到资源引用文件R.java，但是在开发者通过proguard等混淆/优化工具处理apk时，proguard可能会将R.java删除，如果遇到这个问题，请添加如下配置：
#-keep public class [您的应用包名].R$*{
#public static final int *;
#}
# 友盟统计混淆---- end -----
#友盟分享
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}


-keep class com.facebook.**
-keep class com.facebook.** { *; }
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.mm.opensdk.** {
   *;
}
-keep class com.tencent.wxop.** {
   *;
}
-keep class com.tencent.mm.sdk.** {
   *;
}

-keep class com.twitter.** { *; }

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep class com.kakao.** {*;}
-dontwarn com.kakao.**
-keep public class com.umeng.com.umeng.soexample.R$*{
    public static final int *;
}
-keep public class com.linkedin.android.mobilesdk.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.umeng.socialize.impl.ImageImpl {*;}
-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class com.linkedin.** { *; }
-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
-keepattributes Signature

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
#------------------  友盟 end -----------------------------------------------
#------------------  托管经营 省市区选择 start ---------------------------------
# 保留实体类和成员不被混淆
-keep class com.ct.custody_operation.bean.**{
    *;
}
#------------------  托管经营 省市区选择 start ----------------------------------
#------------------  countly 统计埋点 start -----------
-keep class org.openudid.** { *; }
#------------------  countly 统计埋点 end   -----------

#------------------  base-event-filter 统计埋点 start -----------
#保留注解不被混淆
-keep @com.ct.base_event_filter.target.DoubleClick class * {*;}
-keepclassmembers class * {
    @com.ct.base_event_filter.target.DoubleClick <methods>;
}
-keep @com.ct.base_event_filter.target.EventPathClick class *{*;}
-keepclassmembers class * {
    @com.ct.base_event_filter.target.EventPathClick <methods>;
}
-keep @com.ct.base_event_filter.target.EventTypeClick class *{*;}
-keepclassmembers class * {
    @com.ct.base_event_filter.target.EventTypeClick <methods>;
}
#保留相关类不被混淆
-keep class * implements com.okeyun.adapter.base.ItemViewDelegate
-keep class com.ct.base_component_shopkeeper.helper.H5RouterHelper { *; }
-keep class com.okeyun.adapter.** { *;}

-keep public class * extends com.okeyun.adapter.MultiItemTypeAdapter {
    *;
}

-keep class com.ct.base_event_filter.ClickFilter{ *;}

-keepattributes MethodParameters

-keep class org.aspectj.** { *;}
# aop注解
#-adaptclassstrings
#-keepattributes InnerClasses, EnclosingMethod, Signature, *Annotation*
#-keepnames @org.aspectj.lang.annotation.Aspect class * {
#    ajc* <methods>;
#}
#-keepclassmembers class ** {
#    @com.huaying.aop.login.aspect.LoginFilter <methods>;
#}

#------------------  base-event-filter 统计埋点 end   -----------