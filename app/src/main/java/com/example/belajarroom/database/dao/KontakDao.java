package com.example.belajarroom.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.belajarroom.database.entitas.kontak;
import com.example.belajarroom.database.entitas.recycle;

import java.util.List;

@Dao
public interface KontakDao {
    @Query("SELECT * FROM kontak")
    List<kontak>getAll();

    @Query("INSERT INTO kontak (name,nomor) VALUES(:name, :nomor)")
    void insertAll(String name, String nomor);

    @Delete
    void delete(kontak... kontaks);

    @Query("UPDATE kontak SET name=:name , nomor =:nomor WHERE uid =:uid")
    void  update(int uid, String name, String nomor);

    @Query("SELECT * FROM kontak WHERE uid =:uid")
    kontak get(int uid);

    @Query("SELECT * FROM recycle")
    List<recycle>getAllrecycle();

    @Query("INSERT INTO recycle (rfullname,rnumber) VALUES(:name, :nomor)")
    void insertrecycle(String name, String nomor);

    @Delete
    void deleterecycle(recycle... recycles);

    @Query("SELECT * FROM kontak WHERE name = :name")
    kontak getsearch(String name);




}
