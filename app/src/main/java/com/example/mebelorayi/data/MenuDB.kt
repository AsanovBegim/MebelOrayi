package com.example.mebelorayi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mebelorayi.data.ModelMebel.MagazinMebeli
import com.example.mebelorayi.data.dao.MenuDao



@Database(entities = [MagazinMebeli::class], version = 4)
abstract class MenuDB : RoomDatabase() {



    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE `Cafee` (`id` INTEGER, `nameRus` TEXT, `details` TEXT, `ingredients` TEXT, `cost` TEXT, `isOrdered` INTEGER, " +
                            "PRIMARY KEY(`id`))"
                )
            }
        }
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Cafee ADD COLUMN quantity INTEGER")
            }
        }
        lateinit var INSTANCE: MenuDB
        fun getInstance(context: Context): MenuDB {
            return if (::INSTANCE.isInitialized) {
                return INSTANCE
            } else {
                INSTANCE = Room.databaseBuilder(
                    context,
                    MenuDB::class.java,
                    "menu-database.db"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .createFromAsset("menu-database.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE
            }
        }
    }
   abstract fun dao():MenuDao

}