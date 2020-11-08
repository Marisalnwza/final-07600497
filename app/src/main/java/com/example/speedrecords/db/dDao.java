package com.example.speedrecords.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.speedrecords.model.speed;

@Dao
public interface dDao {

    @Query("SELECT * FROM speeds")
    speed[] getAllUsers();

    @Query("SELECT * FROM speeds WHERE id = :id")
    speed getUserById(int id);

    @Insert
    void addSpeed(speed... speeds);


}
