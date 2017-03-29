package com.example.hare.todo1;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hare on 2016/12/7.
 */
public class TODOTab extends Fragment {
    ListView todoListView;
    Button addButton,deleteButton,doneButton;
    private Context mContext;
    private TodoDB db;
    private String title;
    private int count;
    private List<String> name_list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView=inflater.inflate(R.layout.todo_tab, container, false);
        todoListView=(ListView)rootView.findViewById(R.id.todo_list_view);
        addButton=(Button)rootView.findViewById(R.id.button_add);
        deleteButton=(Button)rootView.findViewById(R.id.button_delete);
        doneButton=(Button)rootView.findViewById(R.id.button_done);


        setData();
        todoListView.setAdapter(new TodoListViewAdapter(this.getActivity()));
        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "click"+position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        todoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(mContext, "longClick"+position,
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment addFragment=new AddFragment();
                Bundle bundle=new Bundle();
                bundle.putString("name",title);
                addFragment.setArguments(bundle);
                addFragment.show(getFragmentManager(),"addFragment");

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return rootView;
    }
    public void setData(){
        title=getArguments().getString("name");
        name_list=new ArrayList<String>();
        name_list.add("123");
        db=new TodoDB(getActivity());
        Cursor cursor=db.selectTitleTable(title);
        while (cursor.moveToNext()){
            name_list.add(cursor.getString(0));
        }

    }
    class TodoListViewAdapter extends BaseAdapter {
        public TodoListViewAdapter(Context context){
            mContext=context;
            //this.title_list=title_list;
        }
        @Override
        public View getItem(int position) {
            return null;
        }
        @Override
        public int getCount(){
            count=name_list.size();
            return count;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView title=null;
            //if(convertView==null)//性能
            convertView=LayoutInflater.from(mContext).inflate(R.layout.title_item,null);
            title=(TextView)convertView.findViewById(R.id.item_title);
            title.setText(name_list.get(position));
            return convertView;
        }
    }
}
