package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.ShopDao
import com.example.data.db.models.PersonEntity

@Database(
    entities = [
        PersonEntity::class
    ], version = 1
)
abstract class ShopDataBase: RoomDatabase() {
    abstract fun shopDao(): ShopDao
}