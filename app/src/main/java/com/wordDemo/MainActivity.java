package com.wordDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.apache.poi.hwpf.HWPFDocument;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    @BindView(R.id.tv_word)
    TextView tvWord;
    private String mTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        try {
            mTitle = getWord();
        } catch (IOException e) {
            e.printStackTrace();
        }
        separateString(mTitle);
    }

    private void separateString(String title) {
        String[] level1 = title.split("&");
        Log.e(TAG,"count=" + level1.length);
        for (int i = 0; i < level1.length; i++) {
            String[] level2 = level1[i].split(":");
            Log.v(TAG,"=================================="+ level2.length+"==============================================================================");
            if(level2.length < 8) {
                Log.e(TAG,"this is title" + level2[0]);
            }else if(level2.length > 8){
                Log.e(TAG,"this is title" + level2[0]);
            }else {
                Log.v(TAG,"titleId=" + level2[0]);
                Log.v(TAG,"title1="+level2[1]);
                Log.v(TAG,"title2=" + level2[2]);
                Log.v(TAG,"A." + level2[3]);
                Log.v(TAG,"B."+level2[4]);
                Log.v(TAG,"C."+level2[5]);
                Log.v(TAG,"D."+level2[6]);
                Log.v(TAG,"Correct:"+level2[7]);
            }
        }
    }

    private String getWord() throws IOException {
        InputStream inputStream = getResources().getAssets().open("select.doc");
        HWPFDocument hwpfDocument = new HWPFDocument(inputStream);
        tvWord.setText(hwpfDocument.getDocumentText());
        inputStream.close();
        return hwpfDocument.getDocumentText();
    }
}
