package com.example.testactivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {

    private MyAdapter listItemAdapter; // 适配器
    private ArrayList<PictureInfo> listItems; // 存放文字、图片信息
    String TAG = "MainActivity";
    String html;
    private String mCategroey = "0";
    private String mSize = "0";
    private String mColor = "0";
    private String mPage = "1";
    private RecyclerView recyclerView;
    private Handler handler;
    private  TabLayout tableLayout;
    TabItem tabItem1;
    TabItem tabItem2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);  //实例化rec
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager1 = recyclerView.getLayoutManager();
                    if (layoutManager1 instanceof StaggeredGridLayoutManager) {
                        StaggeredGridLayoutManager lm = (StaggeredGridLayoutManager) layoutManager1;
                        int columnCount = lm.getSpanCount();
                        int positions[] = new int[columnCount];
                        lm.findLastVisibleItemPositions(positions);//添加可见的最后一行的position数据到数组positions
                        for (int i = 0; i < positions.length; i++) {
                            /**
                             * 判断lastItem的底边到recyclerView顶部的距离
                             * 是否小于recyclerView的高度
                             * 如果小于或等于说明滚动到了底部
                             */
                            if (positions[i] >= lm.getItemCount() - columnCount) {

                                Log.i(TAG, "onScrollStateChanged: "+"到底了");
                                LoadData1();

                                //这里写要调用的东西
                            }
                        }

                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
        listItems = new ArrayList<PictureInfo>();
        LoadData();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 5) {
                    listItems = (ArrayList<PictureInfo>) msg.obj;
                    initView();

                } else if(msg.what == 7){
                    listItems = (ArrayList<PictureInfo>) msg.obj;
                    listItemAdapter.setData(listItems);
                    listItemAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MainActivity.this, "已经到头了，看看别的壁纸吧", Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };

    }



    public void LoadData() {
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

                        Message msg = handler.obtainMessage(5);
                        //msg.what=5;
                        // msg1.obj="hello for run";
                        msg.obj = listItems;
                        handler.sendMessage(msg);


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i(TAG, "运行到了2222之后" + e);

                    handler.sendEmptyMessage(6);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i(TAG, "run: " + "提醒");

                        listItemAdapter.notifyDataSetChanged();
                    }
                });
            }

        }).start();
    }
    public void LoadData1() {
        new Thread(new Runnable() {


            @Override
            public void run() {
                try {
                    int page = Integer.valueOf(mPage)+1;
                    Log.i(TAG, "run: page== "+mPage);
                    mPage = String.valueOf(page);
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

                        Message msg = handler.obtainMessage(7);
                        msg.obj = listItems;
                        handler.sendMessage(msg);


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i(TAG, "运行到了2222之后" + e);

                    handler.sendEmptyMessage(6);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i(TAG, "run: " + "提醒");

                        listItemAdapter.notifyDataSetChanged();
                    }
                });
            }

        }).start();
    }
    private void initView() {


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        listItemAdapter = new MyAdapter(listItems);
        recyclerView.setAdapter(listItemAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.variety,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.more_pic){
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}