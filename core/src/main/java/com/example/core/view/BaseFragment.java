package com.example.core.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.core.viewmodel.BaseViewModel;

public abstract class BaseFragment<Binding extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    protected Binding binding;
    protected VM vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        View view = binding.getRoot();
        vm = createVm();

        return view;
    }

    /**
     * 创建viewmodel
     *
     * @return
     */
    protected abstract VM createVm();

    protected abstract int getLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
