package com.example.finalce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.adapter.BaseAdapter;
import com.example.adapter.BaseViewHolder;
import com.example.finalce.R;
import com.example.finalce.databinding.ItemFinalceBinding;
import com.example.finalce.entity.FinalceEntity;

public class FinalceListAdapter extends BaseAdapter<FinalceEntity, BaseViewHolder> {
    public FinalceListAdapter(Context _context) {
        super(_context);
    }

    @Override
    protected BaseViewHolder createVH(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_finalce, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    protected void bindVH(BaseViewHolder holder, int position) {
        ItemFinalceBinding bingding = (ItemFinalceBinding) holder.getBingding();
        bingding.setVariable(com.example.finalce.BR.fina,dataSource.get(position));
    }
}
