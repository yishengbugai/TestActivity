package com.example.testactivity;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

public class Main2Activity extends AppCompatActivity {

    private Main2Adapter listItemAdapter; // 适配器
    private ArrayList<PictureInfo> listItems; // 存放文字、图片信息
    String TAG = "Main2Activity";
    private String mCategroey = "2338";
    private String mSize = "0";
    private String mColor = "0";
    private String mPage = "1";
    private RecyclerView recyclerView;
    private Handler handler;
    private String variety []={"明星","节日","美女","风景","汽车","可爱","唯美","苹果","动漫","爱情","动态","卡通","搞笑","非主流","创意","游戏","影视","动物"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setBackgroundResource(R.mipmap.jiazai);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);  //实例化rec

        listItems = new ArrayList<PictureInfo>();
        LoadData();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 9) {
                    listItems = (ArrayList<PictureInfo>) msg.obj;
                    initView();

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
                        for (int i = 0; i < 18; i++) {
                        Document document = Jsoup.connect("http://www.win4000.com/mobile_" + mCategroey + "_" + mColor + "_" + mSize + "_" + mPage + ".html").get();
                        Elements elements = document.select("div.Left_bar ul li");   //解释
                            Element element=elements.get(1);

                            String image = element.select("a").first().children().first().attr("data-original");
                            String url =  mCategroey;
                            Log.d("图片", image);
                            Log.d("连接", url);
                            Log.d("标题", variety[i]);

                            PictureInfo pictureInfo = new PictureInfo();
                            pictureInfo.setImage(image);
                            pictureInfo.setUrl(url);
                            pictureInfo.setTitle(variety[i]);
                            Log.i(TAG, "run: " + "放入pictureInfo");
                            listItems.add(pictureInfo);

                            int Categroey = Integer.valueOf(mCategroey)+1;
                            Log.i(TAG, "run: page== "+mCategroey);
                            mCategroey = String.valueOf(Categroey);;
                        }

                            Message msg = handler.obtainMessage(9);
                            msg.obj = listItems;
                            handler.sendMessage(msg);



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
        listItemAdapter = new Main2Adapter(listItems);
        recyclerView.setAdapter(listItemAdapter);
    }

}