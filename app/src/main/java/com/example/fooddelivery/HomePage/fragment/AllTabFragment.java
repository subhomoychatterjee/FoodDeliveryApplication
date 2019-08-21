package com.example.fooddelivery.HomePage.fragment;

// Lily: Designed UI. Set fragment replacement. Implemented custom animation.
// Xiao: implemented data request and onClickListener for each adapter.


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AllTabFragment extends Fragment {


    private String baseUrl = "http://rjtmobile.com/ansari/fos/fosapp/fos_food_loc.php?city=";
    private String TAG = "ALLFOOD";


    ArrayList<Food> foods = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private AllFoodAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    public AllTabFragment() {
        // Required empty public constructor
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


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_all);
        mRecyclerView.setHasFixedSize(false);
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

                    for (int i = 0; i <10; i++) {
                        //JSONObject c = foodsJsonArr.getJSONObject(i);
                        String id = String.valueOf(i);
                        String name = "Food"+i;
                        String recepiee = "None";
                        String price = "100";
                        String category = "Category"+(i%3);
                        String thumb = "FoodThumb";
                        final Food curFood = new Food();
                        curFood.setCategory(category);
                        curFood.setName(name);
                        curFood.setRecepiee(recepiee);
                        curFood.setPrice(Double.valueOf(price));
                        curFood.setId(Integer.valueOf(id));
                        curFood.setImageUrl(thumb);

                        foods.add(curFood);
//                        Log.e("Current Food", curFood.getName());

//                        ImageLoader imageLoader = VolleyController.getInstance().getImageLoader();
//                        imageLoader.get(thumb, new ImageLoader.ImageListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.e(TAG, "Image Load Error: " + error.getMessage());
//                            }
//                            @Override
//                            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
//                                if (response.getBitmap() != null) {
//                                    curFood.setImage(response.getBitmap());
////                                    Log.e("SET IMAGE", curFood.getName());
//                                    adapter.notifyData(foods);
//                                }
//                            }
//                        });
                        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.logo1);
                        curFood.setImage(largeIcon);
                        foods.get(i).setImage(curFood.getImage());
                    }

                }catch (Exception e){
                    System.out.println(e);
                }







//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, buildUrl(), null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                Log.d(TAG, jsonObject.toString());
//
//                try{
//                    JSONArray foodsJsonArr = jsonObject.getJSONArray("Food");
//                    for (int i = 0; i < foodsJsonArr.length(); i++) {
//                        JSONObject c = foodsJsonArr.getJSONObject(i);
//                        String id = c.getString("FoodId");
//                        String name = c.getString("FoodName");
//                        String recepiee = c.getString("FoodRecepiee");
//                        String price = c.getString("FoodPrice");
//                        String category = c.getString("FoodCategory");
//                        String thumb = c.getString("FoodThumb");
//                        final Food curFood = new Food();
//                        curFood.setCategory(category);
//                        curFood.setName(name);
//                        curFood.setRecepiee(recepiee);
//                        curFood.setPrice(Double.valueOf(price));
//                        curFood.setId(Integer.valueOf(id));
//                        curFood.setImageUrl(thumb);
//
//                        foods.add(curFood);
////                        Log.e("Current Food", curFood.getName());
//
//                        ImageLoader imageLoader = VolleyController.getInstance().getImageLoader();
//                        imageLoader.get(thumb, new ImageLoader.ImageListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.e(TAG, "Image Load Error: " + error.getMessage());
//                            }
//                            @Override
//                            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
//                                if (response.getBitmap() != null) {
//                                    curFood.setImage(response.getBitmap());
////                                    Log.e("SET IMAGE", curFood.getName());
//                                    adapter.notifyData(foods);
//                                }
//                            }
//                        });
//                        foods.get(i).setImage(curFood.getImage());
//                    }
//
//                }catch (Exception e){
//                    System.out.println(e);
//                }
//                MainActivity.disPDialog();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                VolleyLog.d(TAG, "ERROR" + volleyError.getMessage());
//                Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
//                MainActivity.disPDialog();
//            }
//        });
//        VolleyController.getInstance().addToRequestQueue(jsonObjReq);

        MainActivity.disPDialog();
    }

    private String buildUrl() {
        return baseUrl + "Pritam";
    }

}
