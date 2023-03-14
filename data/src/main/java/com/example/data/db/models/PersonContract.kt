package com.example.data.db.models

object PersonContract {

    const val PERSON_TABLE_NAME = "person"

    object Columns{
        const val PERSON_ID = "person_id"
        const val PERSON_FIRST_NAME = "first_name"
        const val PERSON_LAST_NAME = "last_name"
        const val PERSON_EMAIL = "email"
    }
}