package com.ssnatech.kiasvayuvajra

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Bus::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun busDao(): BusDao

    private class BusDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var busDao = database.busDao()

                    // Delete all content here.
                    busDao.deleteAll()

//                    // Add sample words.
//                    var word = Word("Hello")
//                    busDao.insert(word)
//                    word = Word("World!")
//                    busDao.insert(word)
//
//                    // TODO: Add your own words!
//                    word = Word("TODO!")
//                    busDao.insert(word)
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bus_database"
                )
                    .addCallback(BusDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
    }
}
