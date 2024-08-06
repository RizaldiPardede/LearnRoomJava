package com.example.belajarroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.belajarroom.Adapter.kontakadapter;
import com.example.belajarroom.database.AppDatabase;
import com.example.belajarroom.database.entitas.kontak;
import com.example.belajarroom.database.entitas.recycle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btntambah,btnrecycle,btnsearch;
    private AppDatabase database;
    private com.example.belajarroom.Adapter.kontakadapter kontakadapter;
    private List<kontak> list = new ArrayList<>();
    private AlertDialog.Builder dialog;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btntambah = findViewById(R.id.btn_tambah);
        btnrecycle = findViewById(R.id.btn_recycle);
        recyclerView = findViewById(R.id.recycler_view);
        btnsearch = findViewById(R.id.btnsearch);
        search =  findViewById(R.id.search);


        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.kontakDao().getAll());
        kontakadapter = new kontakadapter(list,getApplicationContext());
        kontakadapter.setDialog(new kontakadapter.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit","Hapus","Panggil"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity.this,TambahActivity.class);
                                intent.putExtra("uid",list.get(position).uid);
                                startActivity(intent);

                                break;
                            case 1:
                                kontak kontak = list.get(position);
                                database.kontakDao().insertrecycle(kontak.fullname,kontak.nomor);
                                database.kontakDao().delete(kontak);
                                onStart();
                                break;
                            case 2:
                                kontak kontak1 = list.get(position);
                                Intent tlp = new Intent (Intent.ACTION_DIAL, Uri.parse("tel:"+kontak1.nomor));
                                startActivity(tlp);
                        }
                    }
                });
                dialog.show();

            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(kontakadapter);


        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

        btnrecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,recycleActivity.class));
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (database.kontakDao().getsearch(search.getText().toString()) != null){
                    list.clear();
                    list.add(database.kontakDao().getsearch(search.getText().toString()));
                    kontakadapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Kontak yang anda cari tidak ditemukan", Toast.LENGTH_SHORT).show();
                    onStart();
                }

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.kontakDao().getAll());
        kontakadapter.notifyDataSetChanged();
    }
}