package kh618.statisticscs;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kh618 on 3/12/2018.
 *
 * Connect to phpMyAdmin database
 */

public class ConnectDB extends AsyncTask<String,String,String> {
    private String link;
    private Context context;
    InternalDB db ;

    public ConnectDB(Context context,String link) {
        this.link = link;
     //   this.listView=listView;
        this.context=context;
        db = new InternalDB(this.context);
    }

    private JSONArray jsonArray;
    private SubjectDetails[] subjectDetails;
    @Override
    protected String doInBackground(String... strings) {
        URL url;
        HttpURLConnection con;
        try {
             url = new URL(link);
             con = (HttpURLConnection) url.openConnection();
            InputStreamReader streamReader =new InputStreamReader(con.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String read;
            while ((read = bufferedReader.readLine()) != null)
                jsonArray = new JSONArray(read);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }




        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Gson gson = new Gson();
        subjectDetails = gson.fromJson(jsonArray.toString(),SubjectDetails[].class);
        ArrayList<SubjectDetails> detailsArrayList  =new ArrayList<>();
        Collections.addAll(detailsArrayList,subjectDetails);
        db.delete();
        db.InsertArray(detailsArrayList);
      //  listView.setAdapter(new SubjectAdabter(context,R.layout.subject_list,detailsArrayList));
    }
}
