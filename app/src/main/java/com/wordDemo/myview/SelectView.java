package com.wordDemo.myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wordDemo.R;

import org.w3c.dom.Text;

/**
 * Created by zwj on 2017/7/27.
 */

public class SelectView extends View {
    private TextView tvTitle;

    public SelectView(Context context) {
        super(context);
        initView();
    }

    public SelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        this.inflate(getContext(), R.layout.select,null);
        this.tvTitle = (TextView)findViewById(R.id.tv_title);
    }

    public void setTitle(String text) {
        Log.e("TEXT",text +"FF");
        tvTitle.setText(text);
    }

}
