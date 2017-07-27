package com.wordDemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.wordDemo.bean.SelectBean;
import com.wordDemo.db.AppDatabase;
import com.wordDemo.db.SelectLib;
import com.wordDemo.myview.PageIndicatorView;
import com.wordDemo.myview.PageRecyclerView;

import org.apache.poi.hwpf.HWPFDocument;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.cusom_swipe_view)
    PageRecyclerView cusomSwipeView;
    @BindView(R.id.indicator)
    PageIndicatorView indicator;
    private String TAG = "MainActivity";
    private String mTitle = "";
    private int mCount = -1;
    private List<SelectBean> mSelect = null;
    private PageRecyclerView.PageAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
      //  cusomSwipeView.setIndicator(indicator);
        final List<SelectLib> selectLibs = new Select().from(SelectLib.class).queryList();
        // 设置行数和列数
        cusomSwipeView.setPageSize(1, 1);
        // 设置页间距
        cusomSwipeView.setPageMargin(30);
        // 设置数据
        cusomSwipeView.setAdapter(myAdapter = cusomSwipeView.new PageAdapter(selectLibs, new PageRecyclerView.CallBack() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.select, parent, false);
                return new MyHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((MyHolder) holder).tv.setText(selectLibs.get(position).titleId+"");
            }

            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "点击："
                        + selectLibs.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "删除："
                        + selectLibs.get(position).title1, Toast.LENGTH_SHORT).show();
                myAdapter.remove(position);
            }
        }));
//        try {
//            mTitle = getWord();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        separateString(mTitle);
    }

    private void separateString(String title) {
        String[] level1 = title.split("&");
        mCount = level1.length;
        mSelect = new ArrayList<SelectBean>();
        for (int i = 0; i < level1.length; i++) {
            String[] level2 = level1[i].split(":");
            if (level2.length < 8) {
                Log.e(TAG, "this is title" + level2[0]);
            } else if (level2.length > 8) {
                Log.e(TAG, "this is title" + level2[0]);
            } else {
                SelectBean select = new SelectBean();
                int id = Integer.parseInt(level2[0].trim());
                select.setTitleId(id);
                select.setTitle1(level2[1]);
                select.setTitle2(level2[2]);
                select.setAnswerA(level2[3]);
                select.setAnswerB(level2[4]);
                select.setAnswerC(level2[5]);
                select.setAnswerD(level2[6]);
                select.setCorrectAnswer(level2[7]);
                mSelect.add(select);
            }
        }
        setSelectToDB();
    }

    private String getWord() throws IOException {
        InputStream inputStream = getResources().getAssets().open("select.doc");
        HWPFDocument hwpfDocument = new HWPFDocument(inputStream);
        inputStream.close();
        return hwpfDocument.getDocumentText();
    }

    private void setSelectToDB() {
        List<SelectLib> selectLibs = new ArrayList<SelectLib>();
        Delete.table(SelectLib.class);
        for (int i = 0; i < mSelect.size(); i++) {
            SelectLib select = new SelectLib();
            SelectBean bean = mSelect.get(i);
            select.answerA = bean.getAnswerA();
            select.answerB = bean.getAnswerB();
            select.answerC = bean.getAnswerC();
            select.answerD = bean.getAnswerD();
            select.title2 = bean.getTitle2();
            select.title1 = bean.getTitle1();
            select.titleId = bean.getTitleId();
            select.correctAnswer = bean.getCorrectAnswer();
            selectLibs.add(select);
        }
        FlowManager.getDatabase(AppDatabase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<SelectLib>(new ProcessModelTransaction.ProcessModel<SelectLib>() {
                    @Override
                    public void processModel(SelectLib selectLib, DatabaseWrapper wrapper) {
                        selectLib.save();
                    }
                }).addAll(selectLibs).build()).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                Log.e(TAG, error.getMessage());
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                getSelect();
            }
        }).build().execute();
    }

    private void getSelect() {

    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView tv = null;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
