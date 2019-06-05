package com.example.testactivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private static final String TAG = "MyAdapter";
    private ArrayList<PictureInfo> imagelist;
    private Context mContext;
    private ImageView image1;
    private ImageView image2;
    private TextView textViewone;
    private TextView textViewtwo;



    public MyAdapter(ArrayList<PictureInfo> list) {
        imagelist = list;

        Log.i(TAG, "getView: "+"进入Adapter");
    }

    @Override
    public int getCount() {
        return imagelist.size()/2;
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
        if(mContext==null){
            mContext=parent.getContext();
        }

//            if (convertView==null){
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);

        image1 = (ImageView) convertView.findViewById(R.id.image_item1);
        image2 = (ImageView) convertView.findViewById(R.id.image_item2);
        textViewone=(TextView) convertView.findViewById(R.id.textView1);
        textViewtwo=(TextView) convertView.findViewById(R.id.textView2);
        int posi=((position+1)*2-1);
        Glide.with(mContext).load(imagelist.get(posi-1).getImage()).placeholder(R.drawable.jia).into(image1);
        textViewone.setText(imagelist.get(posi-1).getTitle());
        Glide.with(mContext).load(imagelist.get(posi).getImage()).placeholder(R.drawable.jia).into(image2);
        textViewtwo.setText(imagelist.get(posi).getTitle());
        Log.i(TAG, "getView: "+imagelist.get(posi).getTitle());
        //with参数代表上下文，load参数url表示资源的路径，placeholder参数表示等待期间展
        // 示的图片，error参数表示加载异常展示，thumbnail参数表示缩略图，into参数表示加载到的ImageView
//        image1.setOnClickListener(new convertView.onClickListener(){
//
//        }
//        );

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

