package com.lifeng.layoutmanager.callback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.lifeng.layoutmanager.adapter.RecyclerAdapter;
import com.lifeng.layoutmanager.been.Been;

import java.util.List;

/**
 * 项目名称：LayoutManager
 * 类描述：滑动删除的效果
 * 作者：峰哥
 * 创建时间：2016/12/24 16:35
 * 邮箱：470794349@qq.com
 * 修改简介：
 */
public class MoveDeleteTouchCallback extends ItemTouchHelper.SimpleCallback {

    private RecyclerAdapter recyclerAdapter;
    private List<Been> lists;

    public MoveDeleteTouchCallback(int dragDirs, int swipeDirs, RecyclerAdapter recyclerAdapter, List<Been> lists) {
        super(dragDirs, swipeDirs);
        this.recyclerAdapter = recyclerAdapter;
        this.lists = lists;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        recyclerAdapter.notifyDataSetChanged();
        lists.remove(viewHolder.getLayoutPosition());
    }

}
