package kh618.statisticscs;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {
    ImageView logo ;
    Animation an,anDisapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = findViewById(R.id.logo);
        an = AnimationUtils.loadAnimation(this,R.anim.logoanimition);
        anDisapper = AnimationUtils.loadAnimation(this,R.anim.disapper);
        if(isNetworkAvailable()) {
            new ConnectDB(this, getResources().getString(R.string.informationLink)).execute();
        }
        else{
            Toast.makeText(this,"no internet connection",Toast.LENGTH_SHORT).show();
        }
      Thread thread = new Thread(){
          @Override
          public void run() {
              super.run();
              try{
              //   logo.startAnimation(an);
                  sleep(3800);
              } catch (Exception e) {
                  Toast.makeText(SplashScreen.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
              }finally {
                  Intent i = new Intent(SplashScreen.this, Home.class);
                  startActivity(i);
              }
          }
      };
      thread.start();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"We 'll miss you ..",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void user(View view) {
        Intent i = new Intent(SplashScreen.this,User.class);
        startActivity(i);
    }

    public void admin(View view) {
        Intent i = new Intent(SplashScreen.this,Admin.class);
        startActivity(i);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
