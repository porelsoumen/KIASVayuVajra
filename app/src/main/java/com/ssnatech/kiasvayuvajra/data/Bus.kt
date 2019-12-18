package com.ssnatech.kiasvayuvajra

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bus")
data class Bus(val name: String) {
    @PrimaryKey(autoGenerate = true) val id: Int = 0
    @ColumnInfo(name = "distance") val distance: Int = 0
    @ColumnInfo(name = "origin") val origin: String? = null
    @ColumnInfo(name = "journey_time") val journeyTime: String? = null
    @ColumnInfo(name = "journey_dist") val journeyDist: String? = null
    @ColumnInfo(name = "dep_orig") val depOrig: String? = null
    @ColumnInfo(name = "dep_dest") val depDest: String? = null
    @ColumnInfo(name = "arrv_orig") val arrvOrig: String? = null
    @ColumnInfo(name = "arrv_dest") val arrvDest: String? = null
}