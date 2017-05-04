package com.google.mediaplayerdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rajesh Kumar Dawar on 28-03-2017.
 */

class FileAdapter extends ArrayAdapter<FileBean>{
    Context context;
    int resource;
    ArrayList<FileBean> fileList;

    public FileAdapter(Context context,int resource,ArrayList<FileBean> fileList) {
        super(context, resource, fileList);

        this.context= context;
        this.resource = resource;
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView image = (ImageView)view.findViewById(R.id.imageView);
        TextView txtTitle = (TextView)view.findViewById(R.id.textView);

        // Read the ArrayList and get the Object
        FileBean fb = fileList.get(position);
        image.setBackgroundResource(fb.getImage());
                txtTitle.setText(fb.getTitle());
        return view;
    }
}
