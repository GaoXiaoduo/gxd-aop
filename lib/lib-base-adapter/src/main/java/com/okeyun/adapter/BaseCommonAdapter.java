/**
 * Created by zhanghao on 2016-09-04 下午6:16.
 * <p>
 * # # # # # # # # # # # # # # # # # # # # # # # # # # #
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 * # # # # # # # # # # # # # # # # # # # # # # # # # # #
 */
package com.okeyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.okeyun.adapter.base.ItemViewDelegate;
import com.okeyun.adapter.base.ViewHolder;

import java.util.List;

/**
 * 适配器
 *
 * @author zhanghao
 * @version 1.0
 */
public abstract class BaseCommonAdapter<M> extends MultiItemTypeAdapter<M>
{
    protected int mLayoutId;

    protected LayoutInflater mInflater;

    public BaseCommonAdapter (final Context context, final int layoutId, List<M> datas)
    {

        super(context, datas);
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(context);

        addItemViewDelegate(new ItemViewDelegate<M>()
        {
            @Override
            public int getItemViewLayoutId ()
            {

                return layoutId;
            }

            @Override
            public boolean isForViewType (M item, int position)
            {

                return true;
            }

            @Override
            public void convert (ViewHolder holder, M m, int position)
            {

                BaseCommonAdapter.this.convert(holder, m, position);
            }
        });
    }

    protected abstract void convert (ViewHolder holder, M m, int position);
}
