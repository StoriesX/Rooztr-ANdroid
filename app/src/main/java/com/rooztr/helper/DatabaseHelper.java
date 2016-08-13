package com.rooztr.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ed12al on 8/13/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "callRequests";

    private static final String TABLE_REQUEST = "request";
    private static final String TABLE_WAIT = "wait";

    private static final String KEY_ID = "id";
    private static final String KEY_REQUESTER = "requester";
    private static final String KEY_REQUESTEE = "requestee";
    private static final String KEY_START = "start";
    private static final String KEY_END = "requester";
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TIME = "time";

    private static final String CREATE_TABLE_REQUEST = "CREATE TABLE "
            + TABLE_REQUEST + "(" + KEY_ID + " TEXT PRIMARY KEY," + KEY_REQUESTEE + " TEXT," + KEY_REQUESTER
            + " TEXT," + KEY_START + " INTEGER," + KEY_END + " INTEGER," + KEY_STATUS + " INTEGER,"
            + KEY_MESSAGE + " TEXT," + KEY_TIME + " INTEGER" + ")";
    private static final String CREATE_TABLE_WAIT = "CREATE TABLE "
            + TABLE_WAIT + "(" + KEY_ID + " TEXT PRIMARY KEY," + KEY_REQUESTEE + " TEXT," + KEY_REQUESTER
            + " TEXT," + KEY_START + " INTEGER," + KEY_END + " INTEGER," + KEY_STATUS + " INTEGER,"
            + KEY_MESSAGE + " TEXT," + KEY_TIME + " INTEGER" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REQUEST);
        db.execSQL(CREATE_TABLE_WAIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WAIT);
        onCreate(db);
    }
}

