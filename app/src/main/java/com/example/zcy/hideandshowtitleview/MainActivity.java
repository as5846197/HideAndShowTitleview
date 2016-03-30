package com.example.zcy.hideandshowtitleview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View titleView;
    private ListView listView;

    /**
     * ListView滑动动画标记项
     */
    private boolean scrollFlag = false;// 标记是否滑动
    private int lastVisibleItemPosition;// 标记上次滑动位置
    private boolean titleViewIsShow = true;
    private boolean titleViewIsScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        titleView = findViewById(R.id.title_view);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 滑动停止
                        scrollFlag = false;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        scrollFlag = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        scrollFlag = true;
                    default:
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (scrollFlag) {
                    if (firstVisibleItem > lastVisibleItemPosition) {
                        if (!titleViewIsScrolling) {
                            if (titleViewIsShow) {
                                titleViewIsScrolling = true;
                                hideTitleView();
                            }
                        }
                    }
                    if (firstVisibleItem < lastVisibleItemPosition) {
                        if (!titleViewIsScrolling) {
                            if (!titleViewIsShow) {
                                titleViewIsScrolling = true;
                                showTitleView();
                            }
                        }
                    }
                    if (firstVisibleItem == lastVisibleItemPosition) {
                        return;
                    }
                    lastVisibleItemPosition = firstVisibleItem;
                }
            }
        });
    }

    private void hideTitleView() {
        final int height = titleView.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(titleView, "titleViewHide", 0f, 1f);
        animator.setDuration(300);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                titleView.setTranslationY(-height * cVal);
                contentView.setTranslationY(height - height * cVal);
                ViewGroup.LayoutParams params = listView.getLayoutParams();
                contentView.setLayoutParams(params);
                if (cVal == 1) {
                    titleViewIsShow = false;
                    titleViewIsScrolling = false;
                }
            }
        });
    }
    private void showTitleView() {

        final int height = titleView.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(titleView, "titleViewShow", 0f, 1f);
        animator.setDuration(300);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                contentView.setTranslationY(height * cVal);
                ViewGroup.LayoutParams params = listView.getLayoutParams();
                contentView.setLayoutParams(params);
                titleView.setTranslationY(-height * (1 - cVal));
                if (cVal == 1) {
                    titleViewIsShow = true;
                    titleViewIsScrolling = false;
                }
            }
        });
    }


    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        return data;
    }
}
