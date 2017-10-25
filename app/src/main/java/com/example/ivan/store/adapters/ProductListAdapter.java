package com.example.ivan.store.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.*;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ivan.store.objects.CardCategory;
import com.example.ivan.store.R;
import com.example.ivan.store.objects.ProductList;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ivan on 17.10.2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyProductViewHolder> {
    ArrayList<ProductList> cardList = new ArrayList<>();
    Context ctx;


    public ProductListAdapter(ArrayList<ProductList> cardList, Context ctx){
        this.cardList = cardList;
        this.ctx = ctx;
    }

    public void dataSetChanged(ArrayList<ProductList> cardList){
        this.cardList = cardList;
        notifyDataSetChanged();
    }

    public void remove(int position){
        cardList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_list, parent, false);

        return new MyProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyProductViewHolder holder, final int position) {
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  Toast.makeText(ctx, FragmentProducts.data, Toast.LENGTH_SHORT).show();
            }
        });
        holder.name.setText(cardList.get(position).getName());
        holder.price.setText(cardList.get(position).getPrice());
        Glide.with(ctx).load(cardList.get(position).getImgSrc())
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



    public class MyProductViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        TextView price;
        CircleImageView photo;
        public MyProductViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv_product);
            name = (TextView) itemView.findViewById(R.id.name_product);
            photo = (CircleImageView) itemView.findViewById(R.id.image_product);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}