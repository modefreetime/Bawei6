package com.example.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public Context context;
    public List<T> dataSource = new ArrayList<>();

    public BaseAdapter(Context _context) {
        context = _context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createVH(parent, viewType);
    }

    protected abstract VH createVH(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        bindVH(holder, position);
    }

    protected abstract void bindVH(VH holder, int position);

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void loadData(List<T> _Data) {
        if (dataSource != null) {
            dataSource.clear();
            this.dataSource = _Data;
            notifyDataSetChanged();
        }
    }

    public void addDataSource(List<T> _Data) {
        if (dataSource != null && _Data != null) {
            dataSource.addAll(_Data);
            notifyDataSetChanged();
        }
    }



}
