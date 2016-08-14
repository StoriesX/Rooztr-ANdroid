package com.rooztr.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rooztr.model.CallRequest;

import java.util.ArrayList;
import java.util.List;

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
    }

    public void updateRequests(List<CallRequest> crList){
        SQLiteDatabase db = this.getWritableDatabase();
        for(CallRequest cr : crList){
            ContentValues values = cr2cv(cr);
            db.insertWithOnConflict(TABLE_REQUEST, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public void replaceAllRequests(List<CallRequest> crList){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUEST);
        db.execSQL(CREATE_TABLE_REQUEST);
        updateRequests(crList);
    }

    public void updateWaits(List<CallRequest> crList){
        SQLiteDatabase db = this.getWritableDatabase();
        for(CallRequest cr : crList){
            ContentValues values = cr2cv(cr);
            db.insertWithOnConflict(TABLE_WAIT, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public void replaceAllWaits(List<CallRequest> crList){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WAIT);
        db.execSQL(CREATE_TABLE_WAIT);
        updateWaits(crList);
    }

    public List<CallRequest> getAllRequests(){
        List<CallRequest> crList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_REQUEST;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                CallRequest cr = cv2cr(c);
                crList.add(cr);
            } while (c.moveToNext());
        }
        return crList;
    }

    public List<CallRequest> getAllWaits(){
        List<CallRequest> crList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_WAIT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                CallRequest cr = cv2cr(c);
                crList.add(cr);
            } while (c.moveToNext());
        }
        return crList;
    }

    private CallRequest cv2cr(Cursor c){
        CallRequest cr = new CallRequest();
        cr.set_id(c.getString(c.getColumnIndex(KEY_ID)));
        cr.setRequester(c.getString(c.getColumnIndex(KEY_REQUESTER)));
        cr.setRequestee(c.getString(c.getColumnIndex(KEY_REQUESTEE)));
        cr.setStart(c.getLong(c.getColumnIndex(KEY_START)));
        cr.setEnd(c.getLong(c.getColumnIndex(KEY_END)));
        cr.setStatus(c.getInt(c.getColumnIndex(KEY_STATUS)));
        cr.setMessage(c.getString(c.getColumnIndex(KEY_MESSAGE)));
        cr.setSentAt(c.getLong(c.getColumnIndex(KEY_TIME)));
        return cr;
    }

    private ContentValues cr2cv(CallRequest cr){
        ContentValues values = new ContentValues();
        values.put(KEY_ID, cr.get_id());
        values.put(KEY_REQUESTER, cr.getRequester());
        values.put(KEY_REQUESTEE, cr.getRequestee());
        values.put(KEY_START, cr.getStart());
        values.put(KEY_END, cr.getEnd());
        values.put(KEY_STATUS, cr.getStatus());
        values.put(KEY_MESSAGE, cr.getMessage());
        values.put(KEY_TIME, cr.getSentAt());
        return values;
    }
}

