package com.dicoding.aplikasikpu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEntry = findViewById(R.id.btn_entry);
        btnEntry.setOnClickListener(this);

        Button btnList = findViewById(R.id.btn_lihat_data);
        btnList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_entry){
            Intent entryIntent = new Intent(MainActivity.this,EntryFormActivity.class);
            startActivity(entryIntent);
        }
        if(v.getId() == R.id.btn_lihat_data){
            Intent listIntent = new Intent(MainActivity.this,ListDataActivity.class);
            startActivity(listIntent);
        }
    }
}