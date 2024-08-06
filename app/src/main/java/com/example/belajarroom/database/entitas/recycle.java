package com.example.belajarroom.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class recycle {
    @PrimaryKey
    public int idr;

    public String rfullname;

    public String rnumber;
}
