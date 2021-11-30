package com.example.mykino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class Steilesactivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,Dialog.Dialoglistener{

    TextView stiles;
    public int text;
    int count=0;
    int[] selected = new int[10];
    int min = 11;
    int position;
    private long back;
    private Toast backtoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steilesactivity);

        Spinner spinner = findViewById(R.id.spinner6);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.stiles,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("ΣΤΕΙΛΕΣ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void onBackPressed() {

        if(back+2000>System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            return;
        }else{
            backtoast = Toast.makeText(getBaseContext(),"ΑΝ ΒΓΕΙΤΕ ΘΑ ΧΑΣΕΤΕ ΤΑ ΔΕΔΟΜΕΝΑ ΠΟΥ ΚΑΤΑΧΩΡΙΣΑΤΕ", Toast.LENGTH_SHORT);
            backtoast.show();
        }
        back = System.currentTimeMillis();
    }

    public void stilesclicked(View view){
        if(count<10) {
            selected[text-1]++;
        }
        Dialog example = new Dialog();
        example.show(getSupportFragmentManager(), "show dialog1");


    }

    @SuppressLint("SetTextI18n")
    public void randomclicked(View view){
        stiles = findViewById(R.id.stiles);
        int random = new Random().nextInt(10) + 1;
        stiles.setText("ΣΤΕΙΛΗ: "+ random);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manual,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item1) {
            Intent intent = new Intent(this, Infoactivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = Integer.parseInt(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void apply(String name) {
        if(name.equals("result")) {
            for (int i = 0; i < 10; i++) {
                if (selected[i] < min) {
                    min = selected[i];
                    position = i;
                }
            }

            Showresult example = new Showresult();
            Bundle bundle = new Bundle();
            bundle.putString("numbers", "H στήλη σας είναι: " + (position+1));
            example.setArguments(bundle);
            example.show(getSupportFragmentManager(), "show dialog1");
        }else{
            if(count<10){
                count++;
                Toast.makeText(this,"ΕΠΙΛΟΓΗ "+count+"/10",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"ΔΕΝ ΜΠΟΡΕΙΤΕ ΝΑ ΕΠΙΛΕΞΕΤΕ ΑΛΛΕΣ ΣΤΕΙΛΕΣ",Toast.LENGTH_SHORT).show();
            }
        }

    }

}
