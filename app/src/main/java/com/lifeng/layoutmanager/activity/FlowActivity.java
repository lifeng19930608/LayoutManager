package com.lifeng.layoutmanager.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lifeng.layoutmanager.R;
import com.lifeng.layoutmanager.adapter.RecyclerAdapter;
import com.lifeng.layoutmanager.been.Been;
import com.lifeng.layoutmanager.callback.MoveDeleteTouchCallback;
import com.lifeng.layoutmanager.callback.MoveTouchCallback;
import com.lifeng.layoutmanager.click.OnRecyclerItemClickListener;
import com.lifeng.layoutmanager.decoration.DividerGridItemDecoration;
import com.lifeng.layoutmanager.utils.VibratorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LayoutManager
 * 类描述：流式布局的主界面
 * 作者：峰哥
 * 创建时间：2016/12/20 16:43
 * 邮箱：470794349@qq.com
 * 修改简介：
 */
public class FlowActivity extends AppCompatActivity implements MoveTouchCallback.OnDragListener {

    private RecyclerView recyclerView;
    private Button button1;
    private Button button2;
    private List<Been> lists;
    private RecyclerAdapter recyclerAdapter;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        initView();
        initData();
        setListener();
    }

    //布局的初始化
    private void initView() {
        recyclerView = findViewById(R.id.recycle);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
    }

    //数据的初始化
    private void initData() {
        lists = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            lists.add(new Been("收款", R.mipmap.takeout_ic_category_brand));
            lists.add(new Been("转账", R.mipmap.takeout_ic_category_flower));
            lists.add(new Been("余额宝", R.mipmap.takeout_ic_category_fruit));
            lists.add(new Been("手机充值", R.mipmap.takeout_ic_category_medicine));
            lists.add(new Been("医疗", R.mipmap.takeout_ic_category_motorcycle));
            lists.add(new Been("彩票", R.mipmap.takeout_ic_category_public));
            lists.add(new Been("电影", R.mipmap.takeout_ic_category_store));
            lists.add(new Been("游戏", R.mipmap.takeout_ic_category_sweet));
        }
        recyclerAdapter = new RecyclerAdapter(R.layout.item_flow, lists);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(FlowActivity.this, 4));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(FlowActivity.this));
        itemTouchHelper = new ItemTouchHelper(new MoveTouchCallback(recyclerAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        itemTouchHelper = new ItemTouchHelper(new MoveDeleteTouchCallback(0, ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                recyclerAdapter, lists));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(recyclerAdapter);
    }

    //控件的监听
    private void setListener() {
        //添加新的项（在末尾）
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lists.add(new Been("我是新加项", R.mipmap.takeout_ic_category_sweet));
                recyclerAdapter.notifyDataSetChanged();
            }
        });

        //删除最后一项
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lists.size() > 0) {
                    lists.remove(lists.size() - 1);
                    recyclerAdapter.notifyDataSetChanged();
                }
            }
        });

        //自定义的拖动效果监听(具体长按还是短按自己控制)
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                //这里可以控制不可拖动的布局（此时的情况为最后一个不可拖动）
                if (vh.getLayoutPosition() != lists.size() - 1) {
                    itemTouchHelper.startDrag(vh);
                    VibratorUtils.Vibrate(FlowActivity.this, 70);   //震动70ms
                }
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Been been = lists.get(vh.getLayoutPosition());
                Toast.makeText(FlowActivity.this, been.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onFinishDrag() {

    }

}
