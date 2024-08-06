package com.example.belajarroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.belajarroom.Adapter.kontakadapter;
import com.example.belajarroom.Adapter.recycleadapter;
import com.example.belajarroom.database.AppDatabase;
import com.example.belajarroom.database.entitas.kontak;
import com.example.belajarroom.database.entitas.recycle;

import java.util.ArrayList;
import java.util.List;

public class recycleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private AppDatabase database;
    private com.example.belajarroom.Adapter.recycleadapter recycleadapter ;
    private List<recycle> list = new ArrayList<>();
    private AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recyclerView = findViewById(R.id.recycler_view1);
        database = AppDatabase.getInstance(getApplicationContext());

        list.addAll(database.kontakDao().getAllrecycle());
        recycleadapter = new recycleadapter(list,getApplicationContext());
        recycleadapter = new recycleadapter(list,getApplicationContext());
        recycleadapter.setDialog(new kontakadapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Kembalikan","Hapus Permanen"};
                dialog = new AlertDialog.Builder(recycleActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                recycle recycle1 = list.get(position);
                                database.kontakDao().insertAll(recycle1.rfullname,recycle1.rnumber);
                                database.kontakDao().deleterecycle(recycle1);
                                onStart();
                                break;
                            case 1:
                                recycle recycle = list.get(position);
                                database.kontakDao().deleterecycle(recycle);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleadapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.kontakDao().getAllrecycle());
        recycleadapter.notifyDataSetChanged();
    }
}