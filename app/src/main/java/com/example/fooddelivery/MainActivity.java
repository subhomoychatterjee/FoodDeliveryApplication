package com.example.fooddelivery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.fooddelivery.CartPage.CartActivity;
import com.example.fooddelivery.HomePage.fragment.HomeFragment;
import com.example.fooddelivery.controller.ShoppingCartItem;

public class MainActivity extends AppCompatActivity {


    //private static ProgressDialog pDialog;

    public static TextView cartNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){
        //pDialog = new ProgressDialog(this);
       // pDialog.setMessage("Loading...");
        //pDialog.setCancelable(false);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("  Just Eat");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        cartNumber = findViewById(R.id.cart_item_number);
        cartNumber.setText(String.valueOf(ShoppingCartItem.getInstance(this).getSize()));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });

        if(findViewById(R.id.main_fragment_container) != null) {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, homeFragment).commit();
        }
    }

//    public static void showPDialog(){
//        if (!pDialog.isShowing()){
//            pDialog.show();
//        }
//    }
//    public static void disPDialog(){
//        if (pDialog.isShowing()){
//            pDialog.dismiss();
//        }
//    }

}

