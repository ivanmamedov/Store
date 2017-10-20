package com.example.ivan.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 17.10.2017.
 */

public class Preferences {
    private static final String WORKER_DATA = "worker_data";

    private SharedPreferences mSettings;

    public Preferences(Context context) {
        mSettings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setCardCategory(List<CardCategory> w){
        Gson gson = new Gson();
        String jsonStr = gson.toJson(w);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(WORKER_DATA, jsonStr);
        editor.apply();
    }

    public List<CardCategory> getCardCategory(){
        Gson gson = new Gson();
        String jsonStr = mSettings.getString(WORKER_DATA, null);
        Type listType = new TypeToken<List<CardCategory>>(){}.getType();
        if (gson.fromJson(jsonStr, listType) == null)
            return new ArrayList<>();
        else
            return (List<CardCategory>) gson.fromJson(jsonStr, listType);
    }

    public void addCardCategory(CardCategory category){
        int maxId = 0;
        List<CardCategory> categories = getCardCategory();
        if (categories == null)
            categories = new ArrayList<>();
        for (CardCategory w : categories){
            if (w.getId() > maxId)
                maxId = w.getId();
        }
        category.setId(maxId + 1);
        categories.add(category);
        setCardCategory(categories);
    }

    public void deleteCardCategory(int position){
        List<CardCategory> cardCategories = getCardCategory();
        cardCategories.remove(position);
        setCardCategory(cardCategories);
    }
}
