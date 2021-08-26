package com.gxd.aop

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.base_event_filter.target.EventPathClick
import com.gxd.aop.databinding.ActivityMainBinding
import com.gxd.aop.databinding.ActivityTestAdapterBinding
import com.okeyun.adapter.BaseCommonAdapter
import com.okeyun.adapter.base.ViewHolder

class TestAdapterActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityTestAdapterBinding

    val testName:String="我是activity里的名称"

    private var mAdapter: BaseCommonAdapter<String>? = null

    private var mList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding=ActivityTestAdapterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initDate()
        initAdapter()
    }


    private fun initDate(){
        for(i in 0.. 4){
            mList.add("测试 $i")
        }
    }
    private fun initAdapter(){
        mBinding.swipeTarget.layoutManager = LinearLayoutManager(this)
        mAdapter = object :
                BaseCommonAdapter<String>(this, R.layout.fragemt_item_text, mList) {
            override fun convert(holder: ViewHolder, item: String, position: Int) {
                val itemCl = holder.getView<LinearLayout>(R.id.ll_item)
                val tvName = holder.getView<TextView>(R.id.tv_name)

                tvName.text=item

                itemCl.setOnClickListener {
                    test(item)
                }
            }
        }
        mBinding.swipeTarget.adapter=mAdapter
        mBinding.swipeTarget.adapter?.notifyDataSetChanged()
    }

    @EventPathClick(eventPath = "path_14")
    private fun test(item:String){
        Toast.makeText(this,"点击item:$item",Toast.LENGTH_SHORT).show()
    }
}