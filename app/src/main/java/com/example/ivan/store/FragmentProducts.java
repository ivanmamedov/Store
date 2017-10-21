package com.example.ivan.store;


import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProducts extends Fragment  {
    RecyclerView recyclerView;
    MyAdapter adapter;
    String id;
    String name = "";
    String img_src;

    public static String data = "";

    public static ArrayList<CardCategory> cardCategories = new ArrayList<CardCategory>();

    public FragmentProducts() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        while (cardCategories.size() > 0){
            cardCategories.remove(0);
        }

        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                id = "" + jsonObject.get("product_type_id");
                name = "" + jsonObject.get("type_name");
                img_src = "http://5.189.47.208:3000/" + jsonObject.get("img_src");
                cardCategories.add(new CardCategory(id, name, img_src));

               /* id =    "id: " + jsonObject.get("product_type_id") + "\n" +
                        "name: " + jsonObject.get("type_name") + "\n" +
                        "img: " + jsonObject.get("img_src") + "\n";
                name = name + id;*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new MyAdapter(cardCategories, view.getContext());
        recyclerView.setAdapter(adapter);

        //Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();

        return view;
    }

    /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                // Handle activity menu item
                return true;
            default:
                // Handle fragment menu items
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }*/
}


