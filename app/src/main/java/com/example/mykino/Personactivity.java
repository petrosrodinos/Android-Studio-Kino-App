package com.example.mykino;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class Personactivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,Dialog.Dialoglistener {

    private static final int NUM_ROWS = 8;
    private static final int NUM_COLS = 10;
    private static final int liga = 5;
    private static final int pola = 20;
    int count = 0;
    int sel = 0;
    String txt = "";
    int itis=0;
    int full=0;
    int number;
    String text;
    Button[][] buttons = new Button[NUM_ROWS][NUM_COLS];
    int[][] selected = new int[liga][pola+5];
    int[] counting = new int[85];
    private long back;
    private Toast backtoast;
    int random;
    int random1;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personactivity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Objects.requireNonNull(getSupportActionBar()).setTitle("ΚΙΝΟ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateButtons();

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

    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableforbtns);

        for (int row = 0; row != NUM_ROWS; row++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col != NUM_COLS; col++){
                count++;
                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                button.setText(String.valueOf(count));

                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }

        }

    }

    public void clearselected(View view){
        for (int row = 0; row != NUM_ROWS; row++) {
            for (int col = 0; col != NUM_COLS; col++){
                buttons[row][col].getBackground().clearColorFilter();
            }
        }
        for (int row = 0; row != sel; row++) {
            selected[full][row] = 0;
        }
        sel=0;
    }

    public void select(View view) {
        Dialog example = new Dialog();
        example.show(getSupportFragmentManager(), "show dialog1");

    }

    private void gridButtonClicked(int col, int row) {
        if(sel!=pola) {
            if(full<=4) {
                Button button = buttons[row][col];
                button.getBackground().setColorFilter(Color.rgb(255, 165, 0), PorterDuff.Mode.MULTIPLY);
                for (int i = 0; i <= sel; i++) {
                    if (selected[full][i] == Integer.parseInt(String.valueOf(button.getText()))) {
                        itis = 1;
                    }

                }
                if (itis == 0) {
                    number = Integer.parseInt(String.valueOf(button.getText()));
                    selected[full][sel] = number;
                    counting[number]++;
                    sel++;
                    Toast.makeText(this, "ΕΠΙΛΟΓΗ " + sel + "/20", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Ο ΑΡΙΘΜΟΣ ΑΥΤΟΣ ΕΧΕΙ ΗΔΗ ΠΡΟΣΤΕΘΕΙ", Toast.LENGTH_SHORT).show();
                    //button.getBackground().clearColorFilter();
                    //selected[sel]=0;
                    itis = 0;
                    //clicks--;
                }
            }else{
                Toast.makeText(this, "ΔΕΝ ΜΠΟΡΕΙΤΕ ΝΑ ΕΠΙΛΕΞΕΤΕ ΑΛΛΕΣ ΚΛΗΡΩΣΕΙΣ", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "ΔΕΝ ΜΠΟΡΕΙΤΕ ΝΑ ΕΠΙΛΕΞΕΤΕ ΑΛΛΟΥΣ ΑΡΙΘΜΟΥΣ", Toast.LENGTH_SHORT).show();
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


    @Override
    public void apply(String name) {

        if(name.equals("cont")){

                for (int row = 0; row != NUM_ROWS; row++) {
                    for (int col = 0; col != NUM_COLS; col++) {
                        buttons[row][col].getBackground().clearColorFilter();
                    }
                }

                Toast.makeText(this, "ΕΠΙΛΟΓΗ " + (full+1) + "/5", Toast.LENGTH_SHORT).show();
                full++;
                sel = 0;

        }else{
            Arrays.sort(counting);

            for(int i=0;i<5;i++) {
                for (int j = 0; j < Integer.parseInt(text); j++) {
                    random = new Random().nextInt(80) + 1;
                    while (check(random)) {
                        random = new Random().nextInt(80) + 1;
                    }
                    if(j==0) {
                        txt = txt + "Συνδιασμος " + (i + 1) + ": ";
                    }
                    if((i==0 || i==1) && (j<=Integer.parseInt(text)%2)){
                        random1 = new Random().nextInt(40);
                        while (counting[random]==0) {
                            random1 = new Random().nextInt(40);
                        }
                        txt = txt + random1 + " , ";
                    }else {
                        txt = txt + random + " , ";
                    }
                }
                txt=txt+"\n";
            }

           Showresult example = new Showresult();
           Bundle bundle = new Bundle();
           bundle.putString("numbers", txt);
           example.setArguments(bundle);
           example.show(getSupportFragmentManager(), "show dialog1");
           txt="";

        }

    }

    public boolean check(int random){
        for (int i = 0; i < liga; i++) {
            for (int j = 0; j < pola; j++) {
                if(selected[i][j] == random){
                   return true;
                }
            }
        }
        return false;
    }

}
