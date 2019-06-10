package com.example.testactivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.ViewHolder> {
    private static final String TAG = "MoreAdapter";

    private Context mContext;
    private ArrayList<ImageList> PictureList;
    private List<String> permissionList;
    int mposition;


    static   class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView image1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            image1=(ImageView)itemView.findViewById(R.id.image_item1);

        }
    }


    public MoreAdapter(ArrayList<ImageList> list) {
        PictureList = list;

        Log.i(TAG, "getView: "+"进入Adapter");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        if(mContext==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.picture_item,parent,false);
        final ViewHolder holder= new ViewHolder(view);



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionList = new ArrayList<String>();
                mposition= holder.getAdapterPosition();
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                if (!permissionList.isEmpty()){
                    String[] permissions = permissionList.toArray(new String[permissionList.size()]);
                    ActivityCompat.requestPermissions((Activity) mContext,permissions,2);
                }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("设置壁纸");
                builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });
                builder.setPositiveButton("设置壁纸", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "position="+mposition+"");
                        Log.i(TAG, "壁纸链接="+PictureList.get(mposition).getUrl()+"");
                        GetWallPaper.setWallpaper((Activity) mContext,PictureList.get(mposition).getUrl());

                    }
                });
                builder.show();

            }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Glide.with(mContext).load(PictureList.get(position).getUrl()).placeholder(R.drawable.jia).into(viewHolder.image1);
        int mPosition = position;
        Log.e("mPosition:",mPosition+"");
    }


    @Override
    public int getItemCount() {
        return PictureList.size();
    }
}
