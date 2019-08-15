package com.example.fooddelivery.HomePage.fragment;
// Lily: Designed UI.
//       Set fragment replacement.
//       Implemented custom animation.
//       Initialized view and tab switch.
// Xiao: Implemented change city function.
//

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fooddelivery.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;


    private TextView locationView;
    private TextView changeLocView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mSectionsPagerAdapter =  new SectionsPagerAdapter(getChildFragmentManager());
        mTabLayout = view.findViewById(R.id.home_tabLayout);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mViewPager = view.findViewById(R.id.home_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTabLayout.setScrollPosition(position, 0, true);
                mTabLayout.setSelected(true);
                mViewPager.getParent().requestDisallowInterceptTouchEvent(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        locationView = view.findViewById(R.id.home_TV_Location);
        locationView.setText("Pritam");
        changeLocView = view.findViewById(R.id.home_TV_notHere);

        return view;
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    AllTabFragment tab1 = new AllTabFragment(R.color.colorPrimary);
                    return tab1;
                case 1:
                    AllTabFragment tab2 = new AllTabFragment(Color.GREEN);
                    return tab2;
                case 2:
                    AllTabFragment tab3 = new AllTabFragment(Color.RED);
                    return tab3;
                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "All";
                case 1:
                    return "Veg";
                case 2:
                    return "Non Veg";
                default:
                    break;
            }
            return null;
        }
    }

}
