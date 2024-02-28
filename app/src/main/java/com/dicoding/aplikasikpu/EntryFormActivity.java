package com.dicoding.aplikasikpu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dicoding.aplikasikpu.db.DbHelper;

public class EntryFormActivity extends AppCompatActivity {
    DbHelper dbHelper;
    private EditText edtNIK, edtName, edtAddress, edtSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_form);

        dbHelper = new DbHelper(this);
        edtNIK = findViewById(R.id.edt_nik);
        edtName = findViewById(R.id.edt_nama);
        edtAddress= findViewById(R.id.edt_address);
        edtSex = findViewById(R.id.edt_sex);

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtNIK.getText().toString().isEmpty()){
                    Toast.makeText(EntryFormActivity.this, "NIK tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(edtName.getText().toString().isEmpty()){
                    Toast.makeText(EntryFormActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(edtAddress.getText().toString().isEmpty()){
                    Toast.makeText(EntryFormActivity.this, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(edtSex.getText().toString().isEmpty()){
                    Toast.makeText(EntryFormActivity.this, "Jenis Kelamin tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else {
                    dbHelper.addUsers(edtNIK.getText().toString(), edtName.getText().toString(),edtAddress.getText().toString(),edtSex.getText().toString());

                    edtNIK.setText("");
                    edtName.setText("");
                    edtAddress.setText("");
                    edtSex.setText("");
                    Toast.makeText(EntryFormActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}