package com.example.hare.todo1;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hare on 2016/12/5.
 */
public class MovieActivity extends AppCompatActivity{
    private View view1,view2,view3;
    private ViewPager viewPager;
    private List<View> viewList;
    //private LocalA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        LayoutInflater inflater=getLayoutInflater();
        view1=inflater.inflate(R.layout.test1,null);
        view2=inflater.inflate(R.layout.test2,null);
        //view3=inflater
        viewList=new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        PagerAdapter pagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }
        };
        viewPager.setAdapter(pagerAdapter);

    }
}
