// File MyApp.kt
// @Author pierre.antoine - 03/02/2020 - No copyright.

package com.uldskull.poc_relationship

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 *   Class "MyApp" :
 *   TODO: Fill class use.
 **/
class MyApp : Application() {

    companion object{
        var database: MyDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()


        class migration_1_2 : Migration(1, 2){
            /**
             * Should run the necessary migrations.
             *
             *
             * This class cannot access any generated Dao in this method.
             *
             *
             * This method is already called inside a transaction and that transaction might actually be a
             * composite transaction of all necessary `Migration`s.
             *
             * @param database The database instance
             */
            override fun migrate(database: SupportSQLiteDatabase) {

            }

        }

        MyApp.database = Room.databaseBuilder(this, MyDatabase::class.java, "poc-db")
            .allowMainThreadQueries()
            .build()


    }
// TODO : Fill class.
}