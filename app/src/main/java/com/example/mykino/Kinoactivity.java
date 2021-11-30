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

import java.util.Objects;


public class Kinoactivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinoactivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setTitle("ΚΙΝΟ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void fromphoto(View view){
        Error example = new Error();
        example.show(getSupportFragmentManager(),"show error");
        //Intent intent = new Intent(this,Photoactivity.class);
        //startActivity(intent);
    }

    public void fromperson(View view){
        Intent intent = new Intent(this,Personactivity.class);
        startActivity(intent);
    }

    public void fromrandom(View view){
        Intent intent = new Intent(this,Randomactivity.class);
        startActivity(intent);
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
