package kh618.statisticscs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static kh618.statisticscs.R.id.tabItem;


public class HomeScreen extends android.support.v4.app.Fragment {
    ListView listView;
    SubjectAdabter adabter;
    InternalDB db;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          v = inflater.inflate(R.layout.fragment_home_screen, container, false);
        db = new InternalDB(v.getContext());
        listView = v.findViewById(R.id.ListShow);
        adabter = new SubjectAdabter(getContext(), R.layout.subject_list, db.getAllInformation());
        if(adabter.getCount() <=0){
            Toast.makeText(v.getContext(), "no data to show", Toast.LENGTH_SHORT).show();
        }
        listView.setAdapter(adabter);
        return v;
    }

    
}
