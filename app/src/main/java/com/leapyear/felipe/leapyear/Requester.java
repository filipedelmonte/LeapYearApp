package com.leapyear.felipe.leapyear;

import android.os.AsyncTask;
import android.util.Pair;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Felipe on 09/12/2014.
 */
public class Requester extends AsyncTask<ArrayList<Year>, Pair<Integer, Boolean>, Void> {

    public OnRequestListenerInterface delegate = null;

    public void setDelegate(OnRequestListenerInterface delegate){
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Pair<Integer, Boolean>... values) {
        super.onProgressUpdate(values);
        delegate.onRequestFinished(values[0]);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
    @Override
    protected Void doInBackground(ArrayList<Year>... params) {

        InputStream inputStream = null;
        ArrayList<Year> randArray = params[0];
        for(int i = 0; i < randArray.size(); i++){
            try {
                URL url = new URL("http://globalbombas.com.br/isleapyear/"+randArray.get(i).getYear());
                inputStream = url.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                publishProgress(new Pair<Integer, Boolean>(i, Boolean.valueOf(reader.readLine().trim())));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try{
                    if(inputStream != null)
                        inputStream.close();
                } catch(Exception squish){
                    squish.printStackTrace();
                }
            }
        }
        return null;
    }
}
