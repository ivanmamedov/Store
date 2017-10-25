package com.example.ivan.store;

import android.os.AsyncTask;

import com.example.ivan.store.fragments.FragmentProductList;
import com.example.ivan.store.fragments.FragmentProducts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ivan on 22.10.2017.
 */

public class ParseTask extends AsyncTask<Void, Void, Void> {
    String data = "";
    String type;
    URL url;

    public ParseTask(String type) {
        this.type = type;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            if (type.equals("category"))
                url = new URL("http://5.189.47.208:3000/category");

            if (type.equals("productList"))
                url = new URL("http://5.189.47.208:3000/product");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (type.equals("category"))
            FragmentProducts.data = this.data;

        if (type.equals("productList"))
            FragmentProductList.data = this.data;
    }
}
