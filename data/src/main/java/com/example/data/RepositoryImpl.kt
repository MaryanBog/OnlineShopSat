package com.example.data

import com.example.data.db.dao.ShopDao
import com.example.data.db.models.PersonEntity
import com.example.data.models.Deals
import com.example.data.models.Flash
import com.example.data.rest.Networking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val shopDao: ShopDao
): Repository {
    override suspend fun loadLatestDeals(): Deals {
        return Networking.api.loadLatestDeals()
    }

    override suspend fun loadFlashSale(): Flash {
        return Networking.api.loadFlashSale()
    }

    override suspend fun savePersonData(personData: PersonEntity) {
        shopDao.insert(personData)
    }

    override suspend fun onAccountExisted(firstName: String): Boolean {
        val personmalEntity = shopDao.onAccountExisted(firstName)
        return firstName == personmalEntity?.firstName
    }
}