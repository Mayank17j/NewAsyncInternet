package com.example.lenovo.newasyncinternet;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ConnectInternetTask extends AsyncTask<String,Void,String> {

    //Space for pre-executing task above doInBackground

    //Provide context is good, if we provide Async class saparatly
    Context ctx;
    public ConnectInternetTask(Context ct){
        ctx=ct;
    }

    @Override
    protected String doInBackground(String... strings) {
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

            in=myConn.getInputStream();

            BufferedReader myBuf=new BufferedReader(new InputStreamReader(in));
            StringBuilder st=new StringBuilder();
            String Line="";

            while ((Line=myBuf.readLine())!=null){
                st.append(Line+" \n");
            }

            myBuf.close();
            in.close();

            return st.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.myText.setText(s);
        super.onPostExecute(s);
    }
}
