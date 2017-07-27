package com.wordDemo.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by zwj on 2017/7/25.
 */
@Table(database = AppDatabase.class)
public class SelectLib extends BaseModel {
    @PrimaryKey(autoincrement = true)
    @Column
    int id;
    @Column
    public int titleId;
    @Column
    public String title1;
    @Column
    public String title2;
    @Column
    public String answerA;
    @Column
    public String answerB;
    @Column
    public String answerC;
    @Column
    public String answerD;
    @Column
    public String correctAnswer;
    @Column(length = 0)
    public int errCount;
    @Column(defaultValue = "")
    public String answerTime;
    @Column(defaultValue = "")
    public String explain;
}
