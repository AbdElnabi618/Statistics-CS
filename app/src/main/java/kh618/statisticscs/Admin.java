package kh618.statisticscs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
///not in used

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

    }

    public void logein(View view) {
        Toast.makeText(Admin.this , "please wait",Toast.LENGTH_SHORT).show();

    }
}
