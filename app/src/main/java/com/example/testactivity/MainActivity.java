package com.example.testactivity;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyAdapter listItemAdapter; // 适配器
    private ArrayList<Map<String, String>> listItems; // 存放文字、图片信息
    String TAG="MainActivity";
    String html;
    private String mCategroey = "0";
    private String mSize = "0";
    private String mColor = "0";
    private String mPage = "1";
//    private static final int UPDATE = 0;
//    private static final int MESSAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.mylist);
        //初始化数据
        listItems=new ArrayList<Map<String,String>>();
        parseHtml(listItems);


        listItemAdapter = new MyAdapter(this,listItems);



        listView.setAdapter(listItemAdapter);  //讲adapter与布局控件对应起来
//        listView.setEmptyView(findViewById(R.id.nodata));
//        listView.setOnItemClickListener(this);
    }

private void parseHtml(List<Map<String, String>> listItems){
    try{  Document document = Jsoup.connect("http://www.win4000.com/mobile_"+ mCategroey + "_" + mColor + "_" + mSize + "_" +mPage + ".html").get();
        Elements elements = document.select("div.Left_bar ul li");

        for (Element element:elements){
            String image = element.select("a").first().children().first().attr("data-original");
            String url = element.select("a").first().attr("href");
            String title = element.select("a").first().attr("title");
            Log.d("图片",image);
            Log.d("连接",url);
            Log.d("标题",title);

            HashMap<String,String> map= new HashMap<String, String>();
            map.put("ItemTitle",url);
            map.put("ItemDetail",image);

            listItems.add(map);

        }}
    catch (IOException e){e.printStackTrace();}
}

    }

