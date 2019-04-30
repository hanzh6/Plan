package com.hansysu.plan.com.hansysu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    //数据库名称
    private static final String DB_NAME="mPlan.db";
    //表名
    private static final String DB_TABLE="mPlan";
    //声明SQLite对象
    private SQLiteDatabase db;
    private static final String DATABASE_CREATE="create table mPlan(" +
            "_id integer primary key autoincrement," +
            "importace integer not null, "+
            "describe text not null," +
            "detail text not null," +
            "start_time text not null," +
            "end_time text not null," +
            "status integer not null)";

    public DbHelper(Context mcontext) {
        super(mcontext, DB_NAME, null, 2);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        this.db=db;
        db.execSQL(DATABASE_CREATE);
    }
    //插入
    public void insert(ContentValues values)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.insert(DB_TABLE, null, values);
        db.close();
    }
    //更新
    public void update(ContentValues values,int id){
        SQLiteDatabase db=getWritableDatabase();
        db.update(DB_TABLE, values, "_id=?", new String[]{String.valueOf(id)});
    }
    //查询
    public Cursor query(int flag)
    {
        Cursor c = null;
        SQLiteDatabase db=getWritableDatabase();
        String[] columns = null;
        String selection = "status=?" ;
        String[] selectionArgs = new  String[]{ String.valueOf(flag) };
        String groupBy = null ;
        String having = null ;
        String orderBy = "importace" ;
        if(flag == 0) {
            columns = null;
            selection = "status=?";
            selectionArgs = new String[]{String.valueOf(flag)};
            orderBy = "importace";
        }
        else if(flag == 1){
            columns = null;
            selection = "status=?";
            selectionArgs = new String[]{String.valueOf(flag)};
            orderBy = "end_time";
        }else{
            columns = null;
            selection = null;
            selectionArgs =null;
            orderBy = null;
        }
        c =   db.query(DB_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy);
        return c;
    }
    //删除
    public void delecte(int id)
    {
        if(db==null)
            db=getWritableDatabase();
        db.delete(DB_TABLE, "_id=?",new String[] {String.valueOf(id)});
    }
    //关闭数据库
    public void close()
    {
        if(db!=null)
            db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

    }

}
