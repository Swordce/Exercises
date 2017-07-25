package com.wordDemo.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by zwj on 2017/7/25.
 */
@Database(name = AppDatabase.NAME,version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "EnglishB";
    public static final int VERSION = 1;
}
