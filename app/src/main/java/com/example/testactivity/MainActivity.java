package com.example.testactivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener{

    private MyAdapter listItemAdapter; // 适配器
    private ArrayList<PictureInfo> listItems; // 存放文字、图片信息
    String TAG = "MainActivity";
    String html;
    private String mCategroey = "0";
    private String mSize = "0";
    private String mColor = "0";
    private String mPage = "1";
    private ListView listView;
    private Handler handler;
    SwipeRefreshLayout swipeRefreshLayout;
//    private static final int UPDATE = 0;
//    private static final int MESSAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list1);
        //初始化数据
        initData();
        listItems = new ArrayList<PictureInfo>();

        listView.setOnScrollListener(this);

        //        parseHtml(listItems);

//        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                try{
//                    Thread.sleep(2000);
//                    mPage=mPage+1;
//                    initText();
//                    Log.i("刷新","233");
//                }
//                catch (Exception e){
//                    Log.i("刷新时候的错误信息：",e+"");
//                }
//
//            }
//        });

        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 5) {
                    listItems = (ArrayList<PictureInfo>) msg.obj;
                    Log.i(TAG, "handleMessage: " + "传回信息");
                    listItemAdapter = new MyAdapter(listItems);
                    Log.i(TAG, "onCreate: " + "运行到了Adapter之前");
                    listView.setAdapter(listItemAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "已经到头了，看看别的壁纸吧", Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };

    }

    //
//private void parseHtml(List<PictureInfo> listItems){
//
//}

    public void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://www.win4000.com/mobile_" + mCategroey + "_" + mColor + "_" + mSize + "_" + mPage + ".html").get();
                    Elements elements = document.select("div.Left_bar ul li");

                    for (Element element : elements) {
                        String image = element.select("a").first().children().first().attr("data-original");
                        String url = element.select("a").first().attr("href");
                        String title = element.select("a").first().attr("title");
                        Log.d("图片", image);
                        Log.d("连接", url);
                        Log.d("标题", title);

                        PictureInfo pictureInfo = new PictureInfo();
                        pictureInfo.setImage(image);
                        pictureInfo.setUrl(url);
                        pictureInfo.setTitle(title);
                        Log.i(TAG, "run: " + "放入pictureInfo");
                        listItems.add(pictureInfo);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i(TAG, "运行到了2222之后" + e);

                    handler.sendEmptyMessage(6);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listItemAdapter.notifyDataSetChanged();

                    }
                });
            }

        }).start();
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
            // 滚动到最后一行了'
            Log.i(TAG, "onScroll: "+"滑到了最后一行");
            int page = Integer.valueOf(mPage) + 1;
            mPage = String.valueOf(page);
            initData();
        }

    }
}

