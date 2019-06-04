package com.example.testactivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private static final String TAG = "MyAdapter";
    private ArrayList<Map<String, String>> imagelist;
    private Context mcontext;
    private ImageView image1;
    private ImageView image2;

    public MyAdapter(Context context, ArrayList<Map<String, String>> list) {
        imagelist = list;
        mcontext = context;
    }

    @Override
    public int getCount() {
        return imagelist.size();
    }

    @Override
    public Object getItem(int position) {
        return imagelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

//            if (convertView==null){
        convertView = LayoutInflater.from(mcontext).inflate(R.layout.list_item, null);

        image1 = (ImageView) convertView.findViewById(R.id.image_item1);
        image2 = (ImageView) convertView.findViewById(R.id.image_item2);

        return convertView;
    }

////            holder.photo.setMaxHeight((width-30)/2);
//        ViewGroup.LayoutParams layoutParams =  holder.photo.getLayoutParams();
//        ViewGroup.LayoutParams layoutParams1 =  holder.lay.getLayoutParams();
//        layoutParams.width=(width-30)/2;
//        layoutParams1.width=(width-30)/2;
//        layoutParams.height=width/2;
////            layoutParams1.height=(width-2)/2;
////            layoutParams1.height=(width-80)/2+85;
//        holder.photo.setLayoutParams(layoutParams);
//        holder.photo1.setLayoutParams(layoutParams);
//
//        holder.lay1.setLayoutParams(layoutParams1);
//        holder.lay.setLayoutParams(layoutParams1);
//
//
//        int position2=position*2+1;
//        int position1=position*2;
//        holder.name.setText(la.get(position2).getNickname());
////            if (la.get(position2).getIs_attention()==0){
////                holder.is_attention.setImageResource(R.mipmap.collection1);
////            }else {
////                holder.is_attention.setImageResource(R.mipmap.collection2);
////            }
//        Holder finalHolder = holder;
//        holder.is_attention.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!LocalStorage.get("user_id").toString().equals(la.get(position2).getUser_id())){
////                        praise(finalHolder.is_attention,la.get(position2).getUser_id());
//                    Intent intent = new Intent(NewPeopleActivity.this, PersonalVideoActivity.class);
//                    LocalStorage.set("video_url",la.get(position2).getEmail());
//                    startActivity(intent);
//                }else {
//
//                }
//
//            }
//        });
//        GlideUtils.getInstance().LoadContextRoundBitmap(NewPeopleActivity.this, la.get(position1).getAvatar(), holder.photo1,0);
//        GlideUtils.getInstance().LoadContextRoundBitmap(NewPeopleActivity.this, la.get(position2).getAvatar(), holder.photo,0);
//        holder.photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NewPeopleActivity.this, HomePageForPersonalActivity.class);
//                intent.putExtra("is_black", 0);
//                intent.putExtra("title_or_null", "title");
//                intent.putExtra("confirm_title", la.get(position2).getNickname());
//                intent.putExtra("user_id", la.get(position2).getUser_id());
//                startActivity(intent);
//            }
//        });
//
//
//        holder.name1= (TextView) convertView.findViewById(R.id.item_list_new_people_nick_name);
//        holder.is_attention1= (ImageView) convertView.findViewById(R.id.item_list_new_people_like);
//
////            holder.photo1.setMaxHeight((width-30)/2);
//
//        holder.name1.setText(la.get(position1).getNickname());
////            if (la.get(position1).getIs_attention()==0){
////                holder.is_attention1.setImageResource(R.mipmap.collection1);
////            }else {
////                holder.is_attention1.setImageResource(R.mipmap.collection2);
////            }
//
//
//
//        holder.photo1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NewPeopleActivity.this, HomePageForPersonalActivity.class);
//                intent.putExtra("is_black", 0);
//                intent.putExtra("title_or_null", "title");
//                intent.putExtra("confirm_title", la.get(position1).getNickname());
//                intent.putExtra("user_id", la.get(position1).getUser_id());
//                startActivity(intent);
//            }
//        });
//        convertView.setTag(holder);
////            }else {
////                holder = (Holder)convertView.getTag();
////            }
//        return convertView;

}

