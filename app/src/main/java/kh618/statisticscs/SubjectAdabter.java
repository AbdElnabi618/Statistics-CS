package kh618.statisticscs;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by kh618 on 3/9/2018.
 */

public class SubjectAdabter extends ArrayAdapter<SubjectDetails> {
    private int resource;
    private Context context;
   // DownloadManager downloadManager;
    View v;
    // SubjectDetails subjectDetails;
    Button button;

    public SubjectAdabter(@NonNull Context context, int resource, @NonNull ArrayList<SubjectDetails> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        final SubjectDetails subjectDetails = getItem(position);

        TextView subName = v.findViewById(R.id.subName);
        TextView subType = v.findViewById(R.id.subType);
        TextView subData = v.findViewById(R.id.subDate);
         button = v.findViewById(R.id.download);


        try {
            subName.setText(subjectDetails.getSubName());
            subData.setText(subjectDetails.getSubData());
            subType.setText(subjectDetails.getSubType() + " : " + subjectDetails.getNum());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                      if (isNetworkAvailable()) {
                          final  DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                          Uri uri = Uri.parse("http://statcs-cs.me/uploads/" + subjectDetails.getfile());
                          DownloadManager.Request request = new DownloadManager.Request(uri);
                          request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                          downloadManager.enqueue(request);
                          Toast.makeText(context, "Downloading..", Toast.LENGTH_SHORT).show();
                      } else {
                          Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                      }
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return v;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}