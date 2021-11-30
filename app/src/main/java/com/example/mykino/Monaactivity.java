package com.example.mykino;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
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

import java.util.Objects;
import java.util.Random;

public class Monaactivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,Dialog.Dialoglistener{

    TextView mona;
    int[] counting = new int[3];
    int count=0;
    public String text;
    int min=11;
    int position;
    public String[] options = new String[]{"ΜΟΝΑ", "ΖΥΓΑ", "ΙΣΟΠΑΛΙΑ"};
    private long back;
    private Toast backtoast;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monaactivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.mona,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Objects.requireNonNull(getSupportActionBar()).setTitle("ΜΟΝ/ΖΥΓΑ");
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

    public void monaclicked(View view){
        if(count<10) {
            switch (text) {
                case "ΜΟΝΑ":
                    counting[0]++;
                    break;
                case "ΖΥΓΑ":
                    counting[1]++;
                    break;
                case "ΙΣΟΠΑΛΙΑ":
                    counting[2]++;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + text);
            }
        }
            Dialog example = new Dialog();
            example.show(getSupportFragmentManager(), "show dialog1");


    }

    public void randomclicked(View view){
        mona = findViewById(R.id.mona);
        int random = new Random().nextInt(3) + 1;
        if(random==1){
            mona.setText("  ΜΟΝΑ");
        }else if(random==2){
            mona.setText("  ΖΥΓΑ");
        }else{
            mona.setText("  ΙΣΟΠΑΛΙΑ");
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

    public void apply(String name) {
        if(name.equals("result")) {
            for (int i = 0; i < 3; i++) {
                if (counting[i] < min) {
                    min = counting[i];
                    position = i;
                }
            }

            Showresult example = new Showresult();
            Bundle bundle = new Bundle();
            bundle.putString("numbers", "H επιλογή σας είναι: " + options[position]);
            example.setArguments(bundle);
            example.show(getSupportFragmentManager(), "show dialog1");
        }else{
            if(count<10){
                count++;
                Toast.makeText(this,"ΕΠΙΛΟΓΗ "+count+"/10",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"ΔΕΝ ΜΠΟΡΕΙΤΕ ΝΑ ΕΠΙΛΕΞΕΤΕ ΑΛΛΕΣ ΚΛΗΡΩΣΕΙΣ",Toast.LENGTH_SHORT).show();
            }

        }
    }

}
