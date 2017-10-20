package com.example.ivan.store;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


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
    Preferences data;
    RecyclerView recyclerView;
    MyAdapter adapter;


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
        //List<CardCategory> cardCategories = data.getCardCategory();
        ArrayList<CardCategory> cardCategories = new ArrayList<CardCategory>();

        cardCategories.add(new CardCategory(0, "Микроволновки", R.drawable.splus45));
        cardCategories.add(new CardCategory(1, "Чайники", R.drawable.splus45));
        cardCategories.add(new CardCategory(2, "Стиральные машины ооооооооооооооооооооооооооооооооооооооо", R.drawable.splus45));
        cardCategories.add(new CardCategory(3, "Фены", R.drawable.splus45));

        adapter = new MyAdapter(cardCategories, view.getContext());
        recyclerView.setAdapter(adapter);



       // data.addCardCategory(cardCategory);
       // adapter.dataSetChanged(data.getCardCategory());
       // recyclerView.scrollToPosition(data.getCardCategory().size() - 1);


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
