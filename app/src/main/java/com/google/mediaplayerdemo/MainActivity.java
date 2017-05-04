package com.google.mediaplayerdemo;

import android.content.Intent;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<FileBean> fileList;
    FileAdapter adapter;


    void initViews()
    {
        listView = (ListView)findViewById(R.id.listView);
        fileList = new ArrayList<>();
    }

    void readFileSys()
    {
        String path= Environment.getExternalStorageDirectory().getAbsolutePath();
        Toast.makeText(this,"path is : " + path,Toast.LENGTH_LONG).show();
        File file = new File(path);
        File[] files = file.listFiles();
        if(files != null)
        {
            for (File f : files)
            {

                FileBean fb = new FileBean();
               /* if (f.isFile()) {
                    fb.setImage(R.drawable.save);
                    fb.setTitle(f.getName());
                } else {
                    fb.setImage(R.drawable.folder);
                    fb.setTitle(f.getName());
                } */

                if(f.isFile() && f.getName().endsWith(".mp3")){
                    fb.setImage(R.drawable.music);
                    fb.setTitle(f.getName());
                    fileList.add(fb);
                }
                //fileList.add(fb);

                adapter = new FileAdapter(this, R.layout.list_item, fileList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(this);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        readFileSys();
    }

    public void onItemClick(AdapterView<?> parent, View view, int i, long l)
    {
        FileBean fb = fileList.get(i);
        Toast.makeText(this,"You selected: "+fb.getTitle(),Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainActivity.this,PlayActivity.class);
        intent.putExtra("keySong",fb.getTitle());
        startActivity(intent);
    }
}
