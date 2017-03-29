package com.example.hare.todo1;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hare on 2016/12/7.
 */
public class TestActivity extends FragmentActivity implements View.OnClickListener{
    private Context mContext;
    private FragmentManager fragmentManager;
    private List<Fragment> mFragment=new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private TextView titleView;
    private String title;
    private TextView TODOTextView;
    private TextView DONETextView;
    private TextView NOTETextView;
    TodoDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initViews();
        fragmentManager = getFragmentManager();
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                 setTabSelected(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
    private void initViews(){
        titleView=(TextView)findViewById(R.id.title);
        Bundle bundle = this.getIntent().getExtras();
        title=bundle.getString("name");
        titleView.setText(title);
        db=new TodoDB(this);
        //check the table ,create if not exists
        db.createTitleTable(title);
        //String[] param=new String[]{"MOVIE_TABLE","harry potter","0","add","don","tod","123","1"};
        //db.insertTitleTable(param);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        TODOTextView=(TextView) findViewById(R.id.todo_textView);
        DONETextView=(TextView) findViewById(R.id.done_textView);
        NOTETextView=(TextView) findViewById(R.id.note_textView);

        TODOTextView.setOnClickListener(this);
        DONETextView.setOnClickListener(this);
        NOTETextView.setOnClickListener(this);

        TODOTab todoTab=new TODOTab();
        todoTab.setArguments(bundle);
        DONETab doneTab=new DONETab();
        doneTab.setArguments(bundle);
        NOTETab noteTab=new NOTETab();
        noteTab.setArguments(bundle);

        mFragment.add(todoTab);
        mFragment.add(doneTab);
        mFragment.add(noteTab);
    }
    private void setTabSelected(int position){
        TODOTextView.setTextColor(ContextCompat.getColor(this,R.color.darkgrey));
        DONETextView.setTextColor(ContextCompat.getColor(this,R.color.darkgrey));
        NOTETextView.setTextColor(ContextCompat.getColor(this,R.color.darkgrey));
        switch (position){
            case 0: TODOTextView.setTextColor(ContextCompat.getColor(this,R.color.darkolivegreen));break;
            case 1: DONETextView.setTextColor(ContextCompat.getColor(this,R.color.darkolivegreen));break;
            case 2: NOTETextView.setTextColor(ContextCompat.getColor(this,R.color.darkolivegreen));break;
            default:break;
        }
        mViewPager.setCurrentItem(position,false);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.todo_textView:
                setTabSelected(0);
                break;
            case R.id.done_textView:
                setTabSelected(1);
                break;
            case R.id.note_textView:
                setTabSelected(2);
                break;
            default:
                break;
        }
    }
}
