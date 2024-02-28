package com.dicoding.aplikasikpu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dicoding.aplikasikpu.db.DbHelper;
import com.dicoding.aplikasikpu.model.Voter;

public class DetailVoterActivity extends AppCompatActivity {

    private TextView tvNik, tvName, tvAddress, tvSex;
    private Voter voter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_voter);

        DbHelper dbHelper = new DbHelper(this);

        tvNik = findViewById(R.id.tv_detail_nik);
        tvName = findViewById(R.id.tv_detail_nama);
        tvAddress = findViewById(R.id.tv_detail_address);
        tvSex = findViewById(R.id.tv_detail_sex);

        Intent intent = getIntent();
        voter = (Voter) intent.getSerializableExtra("voter");

        assert voter != null;
        tvNik.setText(voter.getNik());
        tvName.setText(voter.getName());
        tvAddress.setText(voter.getAddress());
        tvSex.setText(voter.getSex());
    }
}