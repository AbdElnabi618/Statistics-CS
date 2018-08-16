package kh618.statisticscs;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SubjectRecyclerAdapter extends RecyclerView.Adapter<SubjectRecyclerAdapter.Holder> {

    Context context ;
    ArrayList<SubjectDetails> arrayList;

    public SubjectRecyclerAdapter(Context context, ArrayList<SubjectDetails> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.subject_list,parent,false);
        Holder holder =new Holder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final SubjectDetails subjectDetails = arrayList.get(position);
        try {
            holder.subName.setText(subjectDetails.getSubName());
            holder.subData.setText(subjectDetails.getSubData());
            holder.subType.setText(subjectDetails.getSubType() + " : " + subjectDetails.getNum());
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO make manual download with dialog and save in customize folder
                    if (isNetworkAvailable()) {
                        final DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(context.getResources().getString(R.string.downloadLink) + subjectDetails.getfile());
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
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        Button button;
        TextView subData,subName,subType;
        public Holder(View v) {
            super(v);
             subName = v.findViewById(R.id.subName);
             subType = v.findViewById(R.id.subType);
             subData = v.findViewById(R.id.subDate);
             button = v.findViewById(R.id.download);
        }
    }
}
