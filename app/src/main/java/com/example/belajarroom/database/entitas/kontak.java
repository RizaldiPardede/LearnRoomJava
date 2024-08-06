package com.example.belajarroom.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class kontak {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String fullname;

    public String nomor;

}
