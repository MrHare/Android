package com.example.hare.todo1;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by Hare on 2016/12/26.
 */
public class AddFragment extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Light);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        getDialog().setTitle(getArguments().getString("name"));
        View view = inflater.inflate(R.layout.add_dialog_fragement, container);
        return view;
    }
}
