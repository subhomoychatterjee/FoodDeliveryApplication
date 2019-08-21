package com.example.fooddelivery.HomePage.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooddelivery.CartPage.CartActivity;
import com.example.fooddelivery.R;
import com.example.fooddelivery.controller.ShoppingCartItem;
import com.example.fooddelivery.model.Food;


public class FoodDetailFragment extends Fragment {
    TextView mTextId, mTextRecipe, mTextCategory, mTextPrice;
    Button mButtonAdd;
    ImageView mImageView;
    Food food;
    final private String TAG = "FoodDetail";
    CollapsingToolbarLayout collapsingToolbarLayout;


    View view;

    public FoodDetailFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_food_detail, container, false);

        initView();
        initFoodInfo();

        setButtonListener();


        return view;
    }

    private void initView(){
        collapsingToolbarLayout =  view.findViewById(R.id.food_detail_collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Food Name");
        mTextId = view.findViewById(R.id.food_detail_id);
        mTextRecipe = view.findViewById(R.id.food_detail_recipe);
        mTextCategory = view.findViewById(R.id.food_detail_category);
        mTextPrice = view.findViewById(R.id.food_detail_price);
        mButtonAdd = view.findViewById(R.id.food_detail_add);
        mImageView =  view.findViewById(R.id.food_detail_image);
    }

    private void initFoodInfo(){
        food = new Food();
        food.setName(getArguments().getString("foodName"));
        food.setId(getArguments().getInt("foodId"));
        food.setPrice(getArguments().getDouble("foodPrice"));
        food.setRecepiee(getArguments().getString("foodRec"));
        food.setCategory(getArguments().getString("foodCat"));
        food.setImageUrl(getArguments().getString("foodImage"));
        mTextId.setText(String.valueOf(food.getId()));
        mTextCategory.setText(food.getCategory());
        mTextRecipe.setText(food.getRecepiee());
        mTextPrice.setText(String.valueOf(food.getPrice()));
        collapsingToolbarLayout.setTitle(food.getName());

    }

    private void setButtonListener(){
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShoppingCartItem.getInstance(getContext()).addToCart(food);
                TextView cartNumber = getActivity().findViewById(R.id.cart_item_number);
                cartNumber.setText(String.valueOf(ShoppingCartItem.getInstance(getContext()).getSize()));

                new AlertDialog.Builder(getActivity()).setTitle("Successful!").setIcon(
                        android.R.drawable.ic_dialog_info)
                        .setMessage("Add 1 " + food.getName() + " to cart!")
                        .setPositiveButton("Jump to cart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent cartAct = new Intent(getActivity(), CartActivity.class);
                                startActivity(cartAct);
                            }
                        })
                        .setNegativeButton("Continue", null).show();
            }
        });
    }

}
