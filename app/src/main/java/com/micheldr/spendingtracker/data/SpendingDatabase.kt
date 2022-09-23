package com.micheldr.spendingtracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Spending::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class SpendingDatabase : RoomDatabase() {

    abstract fun spendingDao(): SpendingDao

    companion object {
        private var _instance: SpendingDatabase? = null
        private val INSTANCE: SpendingDatabase
            get() = _instance ?: throw IllegalStateException("No database instance")

        fun getDatabase(context: Context): SpendingDatabase {
            if (_instance == null) {
                synchronized(SpendingDatabase::class.java) {
                    if (_instance == null) {
                        _instance = Room.databaseBuilder(
                            context.applicationContext,
                            SpendingDatabase::class.java, "word_database"
                        ) // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            //.addTypeConverter(DateConverter)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
