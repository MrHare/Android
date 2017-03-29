package com.example.hare.todo1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    private List<String> title_list;
    TodoDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get title;
        initData();
        mListView=(ListView)findViewById(R.id.list_view);
        mListView.setAdapter(new MListViewAdapter(this,title_list));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

    }
    public void initData(){
        title_list=new ArrayList<String>();
        db=new TodoDB(this);
        //db.createMainTable();
        //db.insert();
        Cursor cursor=db.select();
        //db.close();会出现error;
        while (cursor.moveToNext()){
            title_list.add(cursor.getString(1));
        }
        db.close();

    }
    public void selectItem(int position){
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("name",title_list.get(position));
        intent.putExtras(bundle);
        intent.setClass(MainActivity.this,TestActivity.class);
        startActivity(intent);
    }
    class MListViewAdapter extends BaseAdapter{
        private Context mContext;
        private List<String> title_list;
        public MListViewAdapter(Context context,List<String> title_list){
            mContext=context;
            this.title_list=title_list;
        }
        @Override
        public View getItem(int position) {
            return null;
        }
        @Override
        public int getCount(){
            return title_list.size();
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView title=null;
            convertView=LayoutInflater.from(mContext).inflate(R.layout.main_item,null);
            title=(TextView)convertView.findViewById(R.id.item_title);
            title.setText(title_list.get(position));
            return convertView;
        }
    }
}

