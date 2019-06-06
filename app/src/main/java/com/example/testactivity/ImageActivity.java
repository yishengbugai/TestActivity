package com.example.testactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageActivity extends AppCompatActivity {
//    private RecyclerView recy_image;
//    private String url;
//    private List<ImageList> iList = new ArrayList<>();
//    private static final int IMAGE = 1;
//    private ImageList moreImage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image);
//        recy_image = (RecyclerView) findViewById(R.id.image_list);
//
//        Intent intent = getIntent();
//        url = intent.getStringExtra("Url");
//
//        RequestImage();
//    }
//
//    private void RequestImage(){
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        OkHttpClient client = new OkHttpClient();
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()){
//                    String result = response.body().string();
//                    ParseHtml(result);
//                }
//            }
//        });
//    }
//
//    private void ParseHtml(String html){
//        Document document = Jsoup.parse(html);
//        Elements elements = document.select("div.scroll-img-cont ul li");
//        for (Element element:elements){
//            String imagelist = element.select("a").first().children().first().attr("data-original");
//            String imageurl0 = imagelist.substring(0,imagelist.indexOf("_"));
//            String imageurl = imageurl0 + ".jpg";
//            moreImage = new ImageList();
//            moreImage.setImagelist(imageurl);
//            Log.d("更多图片",imageurl);
//            iList.add(moreImage);
//            handler.sendEmptyMessage(IMAGE);
//        }
//    }
//
//    private Handler handler = new Handler(){
//        public void handleMessage(Message msg){
//            super.handleMessage(msg);
//            switch (msg.what){
//                case IMAGE:
//                    initImage();
//                    break;
//            }
//        }
//    };
//
//    private void initImage(){
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recy_image.setLayoutManager(linearLayoutManager);
//        MoreAdapter adapter = new MoreAdapter(this,iList);
//        recy_image.setAdapter(adapter);
//    }
}


