package com.example.hare.todo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hare on 2016/12/12.
 */
public class TodoDB extends SQLiteOpenHelper{
    private final static String DATABASE_NAME="TODO1DB";
    private final static int DATABASE_VERSION=1;
    //Map<String,String>sqlParam=new LinkedHashMap<String,String>();
    public TodoDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
    public void createMainTable(){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="CREATE TABLE IF NOT EXISTS MAIN_TABLE(id INTEGER primary key autoincrement,TITLE_NAME TEXT)";
        db.execSQL(sql);
        String[] title_list=new String[]{"MOVIE","BOOK","PLACE"};
        for(String s:title_list){
            insert(s);
        }
    }

    public void insert(String title){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="INSERT INTO MAIN_TABLE(TITLE_NAME) VALUES ('"+title+"')";
        db.execSQL(sql);
    }
    public void delete(){

    }
    public void update(){

    }
    public Cursor select(){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT * FROM MAIN_TABLE";
        Cursor cursor=db.rawQuery(sql,null);
        return cursor;

    }

    public void createTitleTable(String title){
        String sql="CREATE TABLE IF NOT EXISTS " +title+
                "_TABLE(id INTEGER primary key autoincrement," +
                "NAME TEXT," +
                "IS_DONE INTEGER," +
                "ADD_TIME TEXT," +
                "DONE_TIME TEXT," +
                "TODO_TIME TEXT," +
                "REFER TEXT," +
                "REFER_NUMBER INTEGER)";
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(sql);
    }
    public void insertTitleTable(String[] param){
        String sql="INSERT INTO "+param[0]+"(NAME,IS_DONE,ADD_TIME,DONE_TIME,TODO_TIME,REFER,REFER_NUMBER) VALUES('"+
                param[1]+"',"+param[2]+",'"+param[3]+"','"+param[4]+"','"+param[5]+"','"+param[6]+"',"+param[7]+")";
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(sql);
    }
    public void deleteTitleTable(String[] param){

    }
    public void doneTitleTable(String[] param){

    }
    public Cursor selectTitleTable(String title){
        String sql="SELECT NAME FROM "+title+"_TABLE";
        //String sql="SELECT NAME FROM MOVIE_TABLE";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        return cursor;
    }
}
