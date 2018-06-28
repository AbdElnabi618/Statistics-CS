package kh618.statisticscs;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import kh618.statisticscs.R;
import kh618.statisticscs.SubjectAdabter;

/**
 * Created by kh618 on 2/14/2018.
 */

public class ViewPag extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    SubjectAdabter subjectAdabter = null;
    ListView listView;


    public ViewPag(Context context,SubjectAdabter subjectAdabter) {
        this.context = context;
        this.subjectAdabter = subjectAdabter;
    }

    public void setAdabter(SubjectAdabter subjectAdabter) {
        this.subjectAdabter = subjectAdabter;
        listView.setAdapter(subjectAdabter);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viewpager,null);
         listView = view.findViewById(R.id.List_all);
        listView.setAdapter(subjectAdabter);
        if(subjectAdabter.getCount() <=0 ){
            Toast.makeText(context,"No Lecture found",Toast.LENGTH_SHORT).show();
        }
        ViewPager pager =(ViewPager) container;
        pager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);

    }
}

