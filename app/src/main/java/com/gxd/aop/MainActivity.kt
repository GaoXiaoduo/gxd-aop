package com.gxd.aop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ct.base_event_filter.target.DoubleClick
import com.gxd.aop.databinding.ActivityMainBinding
import ly.count.android.sdk.Countly
import ly.count.android.sdk.CountlyConfig
import ly.count.android.sdk.DeviceId

class MainActivity : AppCompatActivity(), View.OnClickListener
{
    private lateinit var mBinding: ActivityMainBinding

    val testName:String="我是activity里的名称"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initEvent()
        initCountly()
    }

    override fun onStart()
    {
        super.onStart()
        Countly.sharedInstance().onStart(this);
    }

    override fun onStop()
    {
        Countly.sharedInstance().onStop()
        super.onStop()
    }
    private fun initCountly(){

       val config = CountlyConfig(
                this,
                "54aac084accd72787e4cc1528b14e7ad34c31495",
                "https://logs.o3cloud.cn"
        )
        if (BuildConfig.DEBUG) {
            config.setLoggingEnabled(true)
        }
        config.setIdMode(DeviceId.Type.OPEN_UDID);
        Countly.sharedInstance().init(config)
        Countly.sharedInstance().setViewTracking(true)
    }
    private fun initEvent(){
        mBinding.btnOne.setOnClickListener(this)
        mBinding.btn2.setOnClickListener(this)
        mBinding.btn3.setOnClickListener(this)
        mBinding.btnDouble.setOnClickListener(object : View.OnClickListener {
            @DoubleClick
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity,"双击啦",Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun goto(){
        val intent:Intent= Intent(this@MainActivity,TestTabActivity::class.java)
        startActivity(intent)
    }
    private fun goto2(){
        val intent:Intent= Intent(this@MainActivity,TestAdapterActivity::class.java)
        startActivity(intent)
    }
    override fun onClick(v: View?)
    {
      when(v?.id){
          R.id.btn_one->{
              Toast.makeText(this,"点击啦",Toast.LENGTH_SHORT).show()
             // Countly.sharedInstance().events().recordEvent("android_gxd_测试", 1, 0.99);
              goto2()
              goto()
          }
          R.id.btn_2->{
              goto2()
          }
          R.id.btn_3->{
              Toast.makeText(this,"3点击啦",Toast.LENGTH_SHORT).show()
              goto()
          }
      }
    }
}