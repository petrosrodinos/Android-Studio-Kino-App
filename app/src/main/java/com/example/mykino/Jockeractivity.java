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

import java.util.Objects;
import java.util.Random;

public class Jockeractivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    public int number1;
    public int number2;
    public String txt="";
    TextView jocker;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jockeractivity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("ΤΖΟΚΕΡ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers1,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner4 = findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.numbers2,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter1);
        spinner4.setOnItemSelectedListener(this);

        jocker = findViewById(R.id.showjocker);
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

    public void randomjocker(View view){

        for(int i=1;i<=5; i++){
            for(int j=1;j<=number1; j++) {
                int random = new Random().nextInt(49) + 1;
                if (j == 1) {
                    txt = txt+"Συνδιασμός "+i+":  ";
                }
                txt = txt + random + " , ";
                jocker.setText(txt);
                if (j == number1) {
                    for (int k = 1; k<= number2; k++) {
                        int random2 = new Random().nextInt(20) + 1;
                        if (k == 1) {
                            txt = txt + "Jocker: ";
                        }
                        txt = txt + random2 + " , ";
                        jocker.setText(txt);
                    }
                }
            }
            txt=txt+"\n\n";
        }
        txt="";
  }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){
            case R.id.spinner3:
                String text1 = parent.getItemAtPosition(position).toString();
                number1 = Integer.parseInt(text1);
                break;


            case R.id.spinner4:
                String text2 = parent.getItemAtPosition(position).toString();
                number2 = Integer.parseInt(text2);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
