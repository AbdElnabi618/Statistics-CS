package kh618.statisticscs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class User extends AppCompatActivity {
EditText editText;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        editText = findViewById(R.id.khaled);
         preferences = this.getSharedPreferences("userName", MODE_PRIVATE);
        preferences.getString("userName","khaled");
    }

    public void next(View view) {
        Toast.makeText(this,"Loding...",Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userName",editText.getText().toString());
        editor.apply();
        Intent i = new Intent(User.this, Home.class);
        startActivity(i);
    }
}
