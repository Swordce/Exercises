package com.wordDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.wordDemo.db.SelectLib;
import com.wordDemo.db.SelectLib_Table;

import org.apache.poi.hwpf.HWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_word)
    TextView tvWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        try {
            getWord();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getWord() throws IOException {
        InputStream inputStream = getResources().getAssets().open("A.doc");
        HWPFDocument hwpfDocument = new HWPFDocument(inputStream);
        tvWord.setText(hwpfDocument.getDocumentText());
        inputStream.close();
    }
}
