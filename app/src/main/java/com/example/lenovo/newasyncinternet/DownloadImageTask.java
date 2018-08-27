package com.example.lenovo.newasyncinternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.BitSet;

public class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {
        //To connect to internet
        String s1=strings[0];//for very first thread
        InputStream in;

        // "URL myUrl=new URL(s1);" put into exception class
        try {
            URL myUrl=new URL(s1);
            HttpURLConnection myConn=(HttpURLConnection) myUrl.openConnection();
            myConn.setReadTimeout(1000);
            myConn.setConnectTimeout(2000);
            myConn.setRequestMethod("GET");
            myConn.connect();

            in=myConn.getInputStream();//Now it contain encode image

            //to Incode Image
            Bitmap myBtMap=BitmapFactory.decodeStream(in);
            //display image
            return myBtMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        MainActivity.myImage.setImageBitmap(bitmap);
    }
}
