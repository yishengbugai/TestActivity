package com.example.testactivity;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String TAG = "MyAdapter";

    private Context mContext;
    int mPosition;
    private ArrayList<PictureInfo> imagelist;
//    private ImageView image1;
//    private ImageView image2;
//    private TextView textViewone;
//    private TextView textViewtwo;

    static   class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView image1;
        TextView textViewone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            image1=(ImageView)itemView.findViewById(R.id.image_item1);
            textViewone=(TextView)itemView.findViewById(R.id.textView1);
        }
    }


    public MyAdapter(ArrayList<PictureInfo> list) {
        imagelist = list;

        Log.i(TAG, "getView: "+"进入Adapter");
    }

    @NonNull
    @Override
     public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        final ViewHolder holder= new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion = holder.getAdapterPosition();
                Intent intent = new Intent(mContext,ImageActivity.class);
                intent.putExtra("Url",imagelist.get(postion).getUrl());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.textViewone.setText(imagelist.get(position).getTitle());
        Glide.with(mContext).load(imagelist.get(position).getImage()).placeholder(R.drawable.jia).into(viewHolder.image1);
        mPosition = position;
        Log.e("mPosition:",mPosition+"");
    }


    @Override
    public int getItemCount() {
        return imagelist.size();
    }

    public ArrayList<PictureInfo> getData() {
        return imagelist;
    }

    public void setData(ArrayList<PictureInfo> list) {
        imagelist = list;
    }

}



