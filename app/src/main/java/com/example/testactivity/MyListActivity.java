package com.example.testactivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyListActivity extends AppCompatActivity {

    private SimpleAdapter listItemAdapter; // 适配器
    String  TAG="MylistActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);


    }
}
