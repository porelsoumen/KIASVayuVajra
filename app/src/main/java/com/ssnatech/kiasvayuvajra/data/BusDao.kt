package com.ssnatech.kiasvayuvajra

import android.os.FileObserver.DELETE
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface BusDao {

        @Query("SELECT * FROM bus")
        fun getAllBuses(): List<Bus>

        @Query("SELECT * FROM bus WHERE id IN (:busIds)")
        fun loadAllByIds(busIds: IntArray): List<Bus>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insertAll(vararg buses: Bus)

        @Delete
        fun delete(bus: Bus)

        @Query("DELETE FROM bus")
        suspend fun deleteAll()

}