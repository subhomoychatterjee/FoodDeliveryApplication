package com.example.fooddelivery.CartPage;
// Done by Lily

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fooddelivery.CartPage.fragment.CartFragment;
import com.example.fooddelivery.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        if(findViewById(R.id.cart_container) != null) {
            CartFragment cartFragment = new CartFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.cart_container,cartFragment).commit();
        }
    }
}
