package com.example.belajarroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.belajarroom.database.AppDatabase;
import com.example.belajarroom.database.entitas.kontak;

public class TambahActivity extends AppCompatActivity {
    private EditText name, number;
    private Button btnsave;
    private AppDatabase database;
    private boolean isEdit = false;
    private int uid = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        name = findViewById(R.id.full_name);
        number = findViewById(R.id.nomor);
        btnsave = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid",0);
        if (uid>0){
            isEdit = true;
            kontak kontak = database.kontakDao().get(uid);
            name.setText(kontak.fullname);
            number.setText(kontak.nomor);
        }
        else{
            isEdit = false;
        }

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.kontakDao().update(uid,name.getText().toString(),number.getText().toString());
                }
                else {database.kontakDao().insertAll(name.getText().toString(),number.getText().toString());
                    }
                finish();
            }
        });

    }
}
