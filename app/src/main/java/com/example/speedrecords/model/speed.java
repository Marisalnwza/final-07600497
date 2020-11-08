package com.example.speedrecords.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "speeds")
public class speed {


    @PrimaryKey(autoGenerate = true)
    public final int id;

    @ColumnInfo(name = "distance")
    public final String distance;

    @ColumnInfo(name = "time")
    public final String time;

    @ColumnInfo(name = "avg")
    public final String average;




    public speed(int id, String distance, String time, String average) {
        this.id = id;
        this.distance=distance;
        this.time=time;
        this.average = average;

    }
}
