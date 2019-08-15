package com.example.fooddelivery.HomePage.fragment;



import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddelivery.HomePage.adapter.AllFoodAdapter;
import com.example.fooddelivery.MainActivity;
import com.example.fooddelivery.R;
import com.example.fooddelivery.model.Food;

import java.util.ArrayList;


public class AllTabFragment extends Fragment {


    private String baseUrl = "http://rjtmobile.com/ansari/fos/fosapp/fos_food_loc.php?city=";
    private String TAG = "ALLFOOD";
    private int color= R.color.colorPrimary;


    ArrayList<Food> foods = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private AllFoodAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    public AllTabFragment(){

    }

    @SuppressLint("ValidFragment")
    public AllTabFragment(int color) {
        // Required empty public constructor
        this.color=color;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_tab, container, false);

        // Request Data From Web Service
        if (foods.size()==0){
            objRequestMethod();
        }


        mRecyclerView = view.findViewById(R.id.recyclerview_all);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setBackgroundColor(color);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AllFoodAdapter(getActivity(), foods);
        adapter.setOnItemClickListener(new AllFoodAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Bundle itemInfo = new Bundle();
                for (int i=0; i<foods.size(); i++){
                    if (foods.get(i).getId() == Integer.valueOf(data)){
                        itemInfo.putInt("foodId", foods.get(i).getId());
                        itemInfo.putString("foodName", foods.get(i).getName());
                        itemInfo.putString("foodCat", foods.get(i).getCategory());
                        itemInfo.putString("foodRec", foods.get(i).getRecepiee());
                        itemInfo.putDouble("foodPrice", foods.get(i).getPrice());
                        itemInfo.putString("foodImage", foods.get(i).getImageUrl());
                        break;
                    }
                }
                FoodDetailFragment foodDetailFragment = new FoodDetailFragment();
                foodDetailFragment.setArguments(itemInfo);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.main_fragment_container, foodDetailFragment)
                        .addToBackStack(AllTabFragment.class.getName())
                        .commit();
            }
        });
        mRecyclerView.setAdapter(adapter);
        return view;
    }


    private void objRequestMethod(){
                MainActivity.showPDialog();




                try{

                    for (int i = 0; i <20; i++) {
                        String id = String.valueOf(i);
                        String name = "Food "+i;
                        String recepiee = "None";
                        String price = String.valueOf(i*100);
                        String category = "Category "+(i%3);
                        String thumb = "Pritam";
                        final Food curFood = new Food();
                        curFood.setCategory(category);
                        curFood.setName(name);
                        curFood.setRecepiee(recepiee);
                        curFood.setPrice(Double.valueOf(price));
                        curFood.setId(Integer.valueOf(id));
                        curFood.setImageUrl(thumb);

                        foods.add(curFood);
                        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.logo1);
                        curFood.setImage(largeIcon);
                        foods.get(i).setImage(curFood.getImage());
                    }

                }catch (Exception e){
                    System.out.println(e);
                }



        MainActivity.disPDialog();
    }

    private String buildUrl() {
        return baseUrl + "Pritam";
    }

}
