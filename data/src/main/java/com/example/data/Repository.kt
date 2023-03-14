package com.example.data

import com.example.data.db.models.PersonEntity
import com.example.data.models.Deals
import com.example.data.models.Flash

interface Repository {
    suspend fun loadLatestDeals(): Deals
    suspend fun loadFlashSale(): Flash

    suspend fun savePersonData(personData: PersonEntity)
    suspend fun onAccountExisted(firstName: String): Boolean
}