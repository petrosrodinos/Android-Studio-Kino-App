package com.example.mykino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import java.util.Random;


public class Randomactivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    public int numbers;
    public String txt="";


    TextView rand1;
    TextView rand2;
    TextView rand3;
    TextView rand4;
    TextView row1;
    TextView row2;
    TextView row3;
    TextView row4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomactivity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);
        row4 = findViewById(R.id.row4);

        row1.setVisibility(View.INVISIBLE);
        row2.setVisibility(View.INVISIBLE);
        row3.setVisibility(View.INVISIBLE);
        row4.setVisibility(View.INVISIBLE);

        getSupportActionBar().setTitle("ΚΙΝΟ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = (String) parent.getItemAtPosition(position);
        numbers = Integer.parseInt(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public  void findrandom(View view){

        rand1 =findViewById(R.id.rand1);
        rand2 = findViewById(R.id.rand2);
        rand3 = findViewById(R.id.rand3);
        rand4 = findViewById(R.id.rand4);

        row1.setVisibility(View.VISIBLE);
        row2.setVisibility(View.VISIBLE);
        row3.setVisibility(View.VISIBLE);
        row4.setVisibility(View.VISIBLE);

        for(int i=0;i<numbers; i++){
            int random = new Random().nextInt(80) + 1;
            txt = random +" , "+txt;
            rand1.setText(txt);

        }
        txt="";

        for(int i=0;i<numbers; i++){
            int random = new Random().nextInt(80) + 1;
            txt = random +" , "+txt;
            rand2.setText(txt);
        }
        txt="";

        for(int i=0;i<numbers; i++){
            int random = new Random().nextInt(80) + 1;
            txt = random +" , "+txt;
            rand3.setText(txt);
        }
        txt="";

        for(int i=0;i<numbers; i++){
            int random = new Random().nextInt(80) + 1;
            txt =random+" , "+txt;
            rand4.setText(txt);
        }
        txt="";
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

}
