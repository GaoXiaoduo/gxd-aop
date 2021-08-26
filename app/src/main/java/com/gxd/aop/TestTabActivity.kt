package com.gxd.aop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.gxd.aop.databinding.ActivityTestTabBinding
import com.gxd.aop.fragment.Test_1_Fragment
import ly.count.android.sdk.Countly

class TestTabActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityTestTabBinding

    val testName: String = "我是activity里的名称"

    private var mViewPager: ViewPager2? = null
    private var mTabLayout: TabLayout? = null

    private var mTabList: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTestTabBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initFragment()
        initTab()
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

    private fun initView()
    {
        mTabLayout = mBinding.tabLayout
        mViewPager = mBinding.viewPager
    }

    private fun initFragment()
    {
      mBinding.viewPager.adapter =
                object : FragmentStateAdapter(supportFragmentManager, lifecycle)
                {

                    override fun getItemCount(): Int
                    {
                        return 2
                    }

                    override fun createFragment(position: Int): Fragment
                    {
                        if(position==0)
                        {
                        return Test_1_Fragment.create()
                        }
                        return Test_1_Fragment.create()
                    }
                }

        //关闭预加载
        val recycler = mBinding.viewPager.getChildAt(0) as RecyclerView
        recycler.layoutManager?.isItemPrefetchEnabled = false
    }
    /**
     * 初始化tab
     */
    private fun initTab() {
        mTabList = mutableListOf()
        mTabList?.add("Fragment_1")
        mTabList?.add("Fragment_2")

        TabLayoutMediator(mTabLayout!!, mViewPager!!) { tab, position ->
            tab.text = mTabList!![position]
           }.attach()
    }

}