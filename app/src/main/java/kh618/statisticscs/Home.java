package kh618.statisticscs;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.LiveFolders;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView title;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        i = R.id.homeScreen;

        title = findViewById(R.id.title);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        openFreg(i);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            isDestroyed();
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            if (isNetworkAvailable()) {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            new ConnectDB(Home.this, "http://statcs-cs.me/api_posts.php").execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        finally {
                            openFreg(i);
                        }
                    }
                };
                thread.start();
            } else {
                Toast.makeText(this, "no internet connection please connect to internet and try again",
                        Toast.LENGTH_SHORT).show();
            }
            return true;
        }else if(id == R.id.about){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.dailog,null))
                   .create().show();
            return true;
        }
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String title = item.getTitle().toString();

        openFreg(id);

        this.title.setText(title);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openFreg(int id) {
        if (id == R.id.Mechanics) {
            Mica mica = new Mica();
            getSupportFragmentManager().beginTransaction().replace(R.id.fram, mica).commit();

        } else if (id == R.id.Statistics) {
            IntroToStatic aStatic = new IntroToStatic();
            getSupportFragmentManager().beginTransaction().replace(R.id.fram, aStatic).commit();
        } else if (id == R.id.Geometry) {
            AnalyticalGeometry geometry = new AnalyticalGeometry();
            getSupportFragmentManager().beginTransaction().replace(R.id.fram, geometry).commit();
        } else if (id == R.id.Algebra) {
            Gabr gabr = new Gabr();
            getSupportFragmentManager().beginTransaction().replace(R.id.fram, gabr).commit();
        } else if (id == R.id.Analysis) {
            RealAnalysis analysis = new RealAnalysis();
            getSupportFragmentManager().beginTransaction().replace(R.id.fram, analysis).commit();
        } else if (id == R.id.Biology) {
            BiologyMath biology = new BiologyMath();
            getSupportFragmentManager().beginTransaction().replace(R.id.fram, biology).commit();
        } else if (id == R.id.homeScreen) {
            HomeScreen homeScreen = new HomeScreen();
            getSupportFragmentManager().beginTransaction().replace(R.id.fram, homeScreen).commit();
        }
        i = id;
    }
    public void contact(View view){
        Uri uri = Uri.parse("http://www.facebook.com/kh618");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    @Override
    public boolean isDestroyed() {
        return super.isDestroyed();
    }
    public void download(View view){
        Button button = (Button) view;
        Toast.makeText(this,"", Toast.LENGTH_SHORT).show();
    }
}
