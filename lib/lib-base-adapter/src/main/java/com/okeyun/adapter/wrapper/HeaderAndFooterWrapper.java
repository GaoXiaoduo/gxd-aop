package com.okeyun.adapter.wrapper;

import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.okeyun.adapter.base.ViewHolder;
import com.okeyun.adapter.util.WrapperUtils;

public class HeaderAndFooterWrapper<M> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int BASE_ITEM_TYPE_HEADER = 100000;

    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();

    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    public HeaderAndFooterWrapper (RecyclerView.Adapter adapter)
    {

        mInnerAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {

        if (mHeaderViews.get(viewType) != null)
        {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            return holder;

        }
        else if (mFootViews.get(viewType) != null)
        {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType (int position)
    {

        if (isHeaderViewPos(position))
        {
            return mHeaderViews.keyAt(position);
        }
        else if (isFooterViewPos(position))
        {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    private int getRealItemCount ()
    {

        return mInnerAdapter.getItemCount();
    }


    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position)
    {

        if (isHeaderViewPos(position))
        {
            return;
        }
        if (isFooterViewPos(position))
        {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount ()
    {

        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    @Override
    public void onAttachedToRecyclerView (RecyclerView recyclerView)
    {

        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback()
        {
            @Override
            public int getSpanSize (
                    GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position)
            {

                int viewType = getItemViewType(position);
                if (mHeaderViews.get(viewType) != null)
                {
                    return layoutManager.getSpanCount();
                }
                else if (mFootViews.get(viewType) != null)
                {
                    return layoutManager.getSpanCount();
                }

                if (oldLookup != null)
                {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onViewAttachedToWindow (RecyclerView.ViewHolder holder)
    {

        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position))
        {
            WrapperUtils.setFullSpan(holder);
        }
    }

    private boolean isHeaderViewPos (int position)
    {

        return position < getHeadersCount();
    }

    private boolean isFooterViewPos (int position)
    {

        return position >= getHeadersCount() + getRealItemCount();
    }


    public void addHeaderView (View view)
    {

        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView (View view)
    {

        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public void clearHeaderViews ()
    {

        if (mHeaderViews.size() > 0)
        {
            mHeaderViews.clear();
            this.notifyDataSetChanged();
        }
    }

    public void clearFooterViews ()
    {

        if (mFootViews.size() > 0)
        {
            mFootViews.clear();
            this.notifyDataSetChanged();
        }
    }

    /**
     * ???????????????FootView
     *
     * @return
     */
    public View getFooterView ()
    {

        return mHeaderViews.size() > 0 ? mHeaderViews.get(0) : null;
    }

    public int getHeadersCount ()
    {

        return mHeaderViews.size();
    }

    public int getFootersCount ()
    {

        return mFootViews.size();
    }


}
