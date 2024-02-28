package com.dicoding.aplikasikpu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dicoding.aplikasikpu.db.DbHelper;
import com.dicoding.aplikasikpu.model.Voter;

public class UpdateActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private EditText edtNik, edtName, edtAddress, edtSex;
    private Button btnUpdate;
    private Voter voter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DbHelper(this);

        edtNik = findViewById(R.id.edt_update_nik);
        edtName = findViewById(R.id.edt_update_name);
        edtAddress = findViewById(R.id.edt_update_address);
        edtSex = findViewById(R.id.edt_update_sex);
        btnUpdate = findViewById(R.id.btn_update);

        Intent intent = getIntent();
        voter = (Voter) intent.getSerializableExtra("user");

        assert voter != null;
        edtNik.setText(voter.getNik());
        edtName.setText(voter.getName());
        edtAddress.setText(voter.getAddress());
        edtSex.setText(voter.getSex());

        btnUpdate.setOnClickListener((View v) -> {
            dbHelper.updateUser(voter.getId(), edtNik.getText().toString(), edtName.getText().toString(), edtAddress.getText().toString(), edtSex.getText().toString());
            Toast.makeText(UpdateActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
            Intent updateIntent = new Intent(UpdateActivity.this, ListDataActivity.class);
            startActivity(updateIntent);
        });
    }
}