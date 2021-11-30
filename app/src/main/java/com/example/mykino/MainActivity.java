package com.example.mykino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import hotchemi.android.rate.AppRate;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    public AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });



        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(3)
                .setRemindInterval(1)
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
        AppRate.with(this).clearAgreeShowDialog();
    }



    public void kinoclicked(View view){
        Intent intent = new Intent(this,Kinoactivity.class);
        startActivity(intent);
    }

    public void jockerclicked(View view){
        Intent intent2 = new Intent(this,Jockeractivity.class);
        startActivity(intent2);
    }

    public void monaclicked(View view){
        Intent intent3 = new Intent(this,Monaactivity.class);
        startActivity(intent3);
    }

    public void stilesclicked(View view){
        Intent intent4 = new Intent(this,Steilesactivity.class);
        startActivity(intent4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.categories,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.item1:
                Intent intent = new Intent(this,Infoactivity.class);
                startActivity(intent);
                return true;

            case R.id.item2:
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));
                }catch(ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id="+getPackageName())));
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


}
