package com.example.lenovo.newasyncinternet;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConnectInternetTask c1;
    DownloadImageTask downImag;
    static TextView myText;//static -> To use without its object
    static ImageView myImage;

    ConnectivityManager myConnManag;
    NetworkInfo myNetInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText=(TextView)findViewById(R.id.textViewMyResult);
        myImage=(ImageView)findViewById(R.id.imageView);

        myConnManag=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        myNetInfo=myConnManag.getActiveNetworkInfo();

    }

    //This Method is to pass to doInbackground
    public void onClickWebPageButton(View view) {
        if (myNetInfo!=null&&myNetInfo.isConnected()){
            c1=new ConnectInternetTask(this);
            c1.execute("www.google.com");

        }
        else {
            Toast.makeText(this,"Internet not conncted",Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickOpenImageButton(View view) {
        if (myNetInfo!=null&&myNetInfo.isConnected()){
            downImag=new DownloadImageTask();
            downImag.execute("https://i.ytimg.com/vi/hip-_JbR888/hqdefault.jpg");

        }
        else {
            Toast.makeText(this,"Internet not conncted",Toast.LENGTH_SHORT).show();
        }
    }
}
