package com.wordDemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wordDemo.myview.SelectView;

import java.util.List;

/**
 * Created by zwj on 2017/7/27.
 */

public class TitleAdapter extends PagerAdapter {
    private List<View> selectLibList;

    public TitleAdapter(List<View> selectLibList) {
        this.selectLibList = selectLibList;
    }

    @Override
    public int getCount() {
        return selectLibList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(selectLibList.get(position));
        Log.e("aaa",selectLibList.get(position)+"");
        return selectLibList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(selectLibList.get(position));
    }
}
