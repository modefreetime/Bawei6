package com.example.finalce.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.view.BaseFragment;
import com.example.finalce.R;
import com.example.finalce.adapter.FinalceListAdapter;
import com.example.finalce.databinding.FinalceLayoutBinding;
import com.example.finalce.entity.FinalceEntity;
import com.example.finalce.viewmodel.FinalceViewModel;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

public class FinalceFragment extends BaseFragment<FinalceLayoutBinding, FinalceViewModel> {

    private TabLayout tabFinalce;
    private RecyclerView rvFinalceMain;
    private SmartRefreshLayout srlFinalce;
    private FinalceListAdapter adapter;

    @Override
    protected FinalceViewModel createVm() {
        return new FinalceViewModel(getActivity());
    }

    @Override
    protected void initBinding() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.finalce_layout;
    }

    @Override
    protected void initView() {
        tabFinalce = view.findViewById(R.id.tab_finalce);
        rvFinalceMain = view.findViewById(R.id.rv_finalce_main);
        srlFinalce = view.findViewById(R.id.srl_Finalce);
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvFinalceMain.setLayoutManager(layoutManager);
        adapter = new FinalceListAdapter(getActivity());
        rvFinalceMain.setAdapter(adapter);
        ArrayList<FinalceEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new FinalceEntity("i" + i));
        }
        adapter.loadData(list);
    }

    @Override
    protected void initEvent() {
        tabFinalce.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabText = tab.getText().toString();
                if (tabText.equals(R.string.tab_one)) {
                    showMsg(tabText + "0");
                } else if (tabText.equals(R.string.tab_two)) {
                    showMsg(tabText + "1");
                } else if (tab.equals(R.string.tab_three)) {
                    showMsg(tabText + "2");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        srlFinalce.setRefreshHeader(new BezierRadarHeader(getActivity()));
        srlFinalce.setRefreshFooter(new BallPulseFooter(getActivity()));
        srlFinalce.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        srlFinalce.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                ArrayList<FinalceEntity> list = new ArrayList<>();
                for (int i = 10; i < 20; i++) {
                    list.add(new FinalceEntity("i" + i));
                }
                adapter.addDataSource(list);
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }
}
