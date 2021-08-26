package com.gxd.aop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.base_event_filter.target.EventPathClick
import com.gxd.aop.R
import com.gxd.aop.databinding.FragmentTest1Binding
import com.okeyun.adapter.BaseCommonAdapter
import com.okeyun.adapter.base.ViewHolder

/**
 *
 * @author gaoxiaoduo
 * @date 4/14/21 6:41 PM
 * @version 1.0
 */
class Test_1_Fragment: Fragment()
{
    private var _binding: FragmentTest1Binding? = null

    //避免了空检查
    private val mBinding get() = _binding!!


    private var mAdapter: BaseCommonAdapter<String>? = null
    private var mList: MutableList<String> = mutableListOf()

    private var mType:Int=3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        initBinding(inflater, container)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDate()
        initAdapter()
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentTest1Binding.inflate(inflater, container, false)
    }

    private fun initDate(){
         for(i in 0.. 4){
             mList.add("测试 $i")
         }
    }
    private fun initAdapter(){
        mBinding.swipeTarget.layoutManager = LinearLayoutManager(activity)
        mAdapter = object :
                BaseCommonAdapter<String>(
                        activity,
                        R.layout.fragemt_item_text,
                        mList
                ) {
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
          Toast.makeText(activity,"点击item:$item",Toast.LENGTH_SHORT).show()
    }
    companion object {
        val TAG: String = Test_1_Fragment::class.java.simpleName
        fun create(): Test_1_Fragment {
            return Test_1_Fragment()
            }
    }
}