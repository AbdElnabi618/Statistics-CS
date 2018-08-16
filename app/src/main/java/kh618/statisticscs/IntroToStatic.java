package kh618.statisticscs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class IntroToStatic extends Fragment {
    ViewPag  pag;
    SubjectRecyclerAdapter adabter;
    SubjectRecyclerAdapter adabter2;
    InternalDB db ;
    View v;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.activity_intro_to_static, container, false);
        db = new InternalDB(v.getContext());
        adabter = new SubjectRecyclerAdapter(getContext(),db.getStatistics("Lecture"));
        adabter2 = new SubjectRecyclerAdapter(getContext(),db.getStatistics("Section"));

         viewPager = v.findViewById(R.id.view);
        pag = new ViewPag(v.getContext(),adabter);
        viewPager.setAdapter(pag);
          tabLayout = v.findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.textColor));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() == 0){
                    pag.setAdabter(adabter);
                    if(adabter.getItemCount() <= 0)
                        Snackbar.make(v, "No " + tab.getText() + " found", Snackbar.LENGTH_SHORT).show();
                }
                else if(tab.getPosition() == 1){
                    pag.setAdabter(adabter2);
                    if(adabter2.getItemCount() <= 0)
                        Snackbar.make(v, "No " + tab.getText() + " found", Snackbar.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //  imageView.setImageResource(R.drawable.slide2);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // imageView.setImageResource(R.drawable.slide3);
            }
        });

        return v;
    }
}
