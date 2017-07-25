package com.wordDemo.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by zwj on 2017/7/25.
 */
@Table(database = AppDatabase.class)
public class SelectLib extends BaseModel{
    @Column
    int id;
    @PrimaryKey
    @Column
    short titleId;
    @Column
    String title;
    @Column
    String answerA;
    @Column
    String answerB;
    @Column
    String answerC;
    @Column
    String answerD;
    @Column
    String correctAnswer;
}
