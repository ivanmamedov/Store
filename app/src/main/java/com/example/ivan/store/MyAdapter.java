package com.example.ivan.store;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.*;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ivan on 17.10.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<CardCategory> cardList = new ArrayList<>();
    Context ctx;

    public MyAdapter(ArrayList<CardCategory> cardList, Context ctx){
        this.cardList = cardList;
        this.ctx = ctx;
    }

    public void dataSetChanged(ArrayList<CardCategory> cardList){
        this.cardList = cardList;
        notifyDataSetChanged();
    }

    public void remove(int position){
        cardList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
       /* if (viewType == 1)
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_grid_small, parent,false);
        else
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_grid, parent,false); */

         v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx,String.valueOf(cardList.get(position).getId()),Toast.LENGTH_SHORT).show();
            }
        });
        holder.name.setText(cardList.get(position).getName());
       // Glide.with(ctx).load("http://code.makery.ch/assets/library/javafx-8-tutorial/part1/addressapp-part1.png")
                //.diskCacheStrategy(DiskCacheStrategy.SOURCE)
            //   .into(holder.photo);
        Glide.with(ctx).load(cardList.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2 == 0)
            return 1;
        else
            return 2;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        CircleImageView photo;
        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            name = (TextView) itemView.findViewById(R.id.name);
            photo = (CircleImageView) itemView.findViewById(R.id.image_category);
        }
    }
}
