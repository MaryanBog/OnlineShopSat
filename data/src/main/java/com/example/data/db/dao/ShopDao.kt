package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.db.models.PersonContract
import com.example.data.db.models.PersonEntity

@Dao
interface ShopDao {

    @Insert
    suspend fun insert(personData: PersonEntity)

    @Query("SELECT * FROM ${PersonContract.PERSON_TABLE_NAME} WHERE ${PersonContract.Columns.PERSON_FIRST_NAME} = :firstName")
    suspend fun onAccountExisted(firstName: String): PersonEntity?

}