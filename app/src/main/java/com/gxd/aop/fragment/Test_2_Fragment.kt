package com.gxd.aop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gxd.aop.R
import com.gxd.aop.databinding.FragmentTest1Binding
import com.gxd.aop.databinding.FragmentTest2Binding

/**
 *
 * @author gaoxiaoduo
 * @date 4/14/21 6:41 PM
 * @version 1.0
 */
class Test_2_Fragment: Fragment(),View.OnClickListener
{
    private var _binding: FragmentTest2Binding? = null

    //避免了空检查
    private val mBinding get() = _binding!!

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
        initEvent()
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentTest2Binding.inflate(inflater, container, false)
    }

    private fun initEvent(){
        mBinding.btnFragmentDouble.setOnClickListener(this)
        mBinding.btnFragment1.setOnClickListener(this)
        mBinding.btnFragment2.setOnClickListener(this)
    }
    override fun onClick(v: View?)
    {
      when(v?.id){
          R.id.btn_fragment_double ->{
              Toast.makeText(activity,"fragment_1 double点击啦", Toast.LENGTH_SHORT).show()
              // Countly.sharedInstance().events().recordEvent("android_gxd_测试", 1, 0.99);
          }
          R.id.btn_fragment_1 ->{
              Toast.makeText(activity,"fragment_1 1点击啦", Toast.LENGTH_SHORT).show()
              // Countly.sharedInstance().events().recordEvent("android_gxd_测试", 1, 0.99);
          }
          R.id.btn_fragment_2->{
              Toast.makeText(activity,"fragment_1 2点击啦", Toast.LENGTH_SHORT).show()
              // Countly.sharedInstance().events().recordEvent("android_gxd_测试", 1, 0.99);
          }
      }
    }

    companion object {
        val TAG: String = Test_2_Fragment::class.java.simpleName
        fun create(): Test_2_Fragment {
            return Test_2_Fragment()
        }
    }
}