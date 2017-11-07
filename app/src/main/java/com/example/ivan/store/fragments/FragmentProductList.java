package com.example.ivan.store.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.store.R;
import com.example.ivan.store.adapters.MyAdapter;
import com.example.ivan.store.adapters.ProductListAdapter;
import com.example.ivan.store.objects.CardCategory;
import com.example.ivan.store.objects.ProductList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProductList extends Fragment {
    RecyclerView recyclerView;
    ProductListAdapter adapter;

    String id = "";
    String name = "";
    String price = "";
    String img_src = "";
    String productTypeId = "";

    String information = "";
    String imgCount = "";
    String count = "";

    public static String typeId = "";
    public String category_id = "";

    public static String data = "";

    public static ArrayList<ProductList> cardProductList = new ArrayList<ProductList>();


    public FragmentProductList(String category_id) {
        // Required empty public constructor
        this.category_id = category_id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_2);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        new ParseTask().execute();

       /* cardProductList.add(new ProductList("1","Телевизор","30000", "http://www.lg.com/ru/images/televisions/md05601252/gallery/medium_02.jpg", "1","ABC","1"));

        adapter = new ProductListAdapter(cardProductList, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged(); */



        return view;
    }

    private class ParseTask extends AsyncTask<String, String, String> {
        String result;
        @Override
        protected String doInBackground(String... strings) {
            result = "";
            try {
                URL url = new  URL("http://5.189.47.208:3000/product");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                while (line != null) {
                    line = bufferedReader.readLine();
                    result = result + line;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
              ArrayList<ProductList> cardProductList = new ArrayList<ProductList>();


            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    id = "" + jsonObject.get("product_id");
                    name = "" + jsonObject.get("product_name");
                    productTypeId = "" + jsonObject.get("product_type_id");
                    price = "" + jsonObject.get("price");
                    img_src = "http://5.189.47.208:3000/" + jsonObject.get("img_src");

                    information = "" + jsonObject.get("information");
                    imgCount = "" + jsonObject.get("img_count");
                    count = "" + jsonObject.get("count");

                    if (productTypeId.equals(category_id))
                        cardProductList.add(new ProductList(id, name, price, img_src, productTypeId, information, imgCount, count));

               /* id =    "id: " + jsonObject.get("product_type_id") + "\n" +
                        "name: " + jsonObject.get("type_name") + "\n" +
                        "img: " + jsonObject.get("img_src") + "\n";
                name = name + id;*/
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



            adapter = new ProductListAdapter(cardProductList, getActivity());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }
}
