package com.example.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = PersonContract.PERSON_TABLE_NAME,
    indices = [
        Index(PersonContract.Columns.PERSON_FIRST_NAME)
    ]
)
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PersonContract.Columns.PERSON_ID)
    val id: Long,
    @ColumnInfo(name = PersonContract.Columns.PERSON_FIRST_NAME)
    val firstName: String,
    @ColumnInfo(name = PersonContract.Columns.PERSON_LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = PersonContract.Columns.PERSON_EMAIL)
    val email: String
)
