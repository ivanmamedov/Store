package com.example.ivan.store.fragments;


import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.store.ParseTask;
import com.example.ivan.store.objects.CardCategory;
import com.example.ivan.store.adapters.MyAdapter;
import com.example.ivan.store.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProducts extends Fragment  {
    RecyclerView recyclerView;
    MyAdapter adapter;
    String id;
    String name = "";
    String img_src;
    String information;

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

        //cardCategories.add(new CardCategory("1", "Многа многа многа многа чайников", "http://www.olegcherne.ru/i/products_section/520/172_1_teapot.jpg"));

        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                id = "" + jsonObject.get("product_type_id");
                name = "" + jsonObject.get("type_name");
                information = "" + jsonObject.get("information");
                img_src = "http://5.189.47.208:3000/" + jsonObject.get("img_src");
                cardCategories.add(new CardCategory(id, name, img_src, information));

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


