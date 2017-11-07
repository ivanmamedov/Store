package com.example.ivan.store.fragments;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.ivan.store.R;
import com.example.ivan.store.objects.ProductList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProduct extends Fragment implements ViewSwitcher.ViewFactory, GestureDetector.OnGestureListener{

    private ImageSwitcher mImageSwitcher;
    private TextView tvName;
    private TextView tvInformation;
    private TextView tvPrice;
    private TextView tvIsStock;
    private Button btnReviews;
    private Button btnAddBasket;

    int position = 0;
    private int[] mImageIds = { R.drawable.side_nav_bar, R.drawable.splus45,
            R.drawable.side_nav_bar };

    private GestureDetector mGestureDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    ProductList productList;

    public FragmentProduct(ProductList productList) {
        // Required empty public constructor
        this.productList = productList;
    }

    public FragmentProduct(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        tvName = view.findViewById(R.id.name_product_fp);
        tvInformation = view.findViewById(R.id.information_product_fp);
        tvPrice = view.findViewById(R.id.price_product_fp);
        tvIsStock = view.findViewById(R.id.is_in_stock_fp);

        btnReviews = view.findViewById(R.id.btn_reviews);
        btnAddBasket = view.findViewById(R.id.btn_add);

        tvName.setText(productList.getName());
        tvInformation.setText(productList.getInformation());
        tvPrice.setText(productList.getPrice());

        try {
            if (Integer.parseInt(productList.getCount()) > 0)
                tvIsStock.setText("Есть в наличии");
            else tvIsStock.setText("Нет в наличии");
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

        mImageSwitcher = view.findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(this);
        mImageSwitcher.setImageResource(mImageIds[0]);

      /*  Glide.with(getActivity()).load("http://mirpozitiva.ru/uploads/posts/2016-09/1474011210_15.jpg")
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into((ImageView) mImageSwitcher.getCurrentView()); */

        mGestureDetector = new GestureDetector(getActivity(), this);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        });


        return view;
    }

    public void setPositionNext() {
        position++;
        if (position > mImageIds.length - 1) {
            position = 0;
        }
    }

    public void setPositionPrev() {
        position--;
        if (position < 0) {
            position = mImageIds.length - 1;
        }
    }



    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // справа налево
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.go_prev_in));
                mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.go_prev_out));
                setPositionNext();
                mImageSwitcher.setImageResource(mImageIds[position]);
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                // слева направо
                mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.go_prev_in1));
                mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.go_prev_out1));
                setPositionPrev();
                mImageSwitcher.setImageResource(mImageIds[position]);
            }
        } catch (Exception e) {
            // nothing
            return true;
        }
        return true;
    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new
                ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        imageView.setBackgroundColor(0xFF000000);
        return imageView;
    }


}
