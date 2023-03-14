package com.example.onlineshopsat.app.di

import android.content.Context
import androidx.room.Room
import com.example.data.Constants
import com.example.data.Repository
import com.example.data.RepositoryImpl
import com.example.data.db.ShopDataBase
import com.example.data.db.dao.ShopDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(shopDao: ShopDao): Repository {
        return RepositoryImpl(shopDao)
    }

    @Singleton
    @Provides
    fun provideShopDataBase(@ApplicationContext context: Context): ShopDataBase {
//          return Room
//            .inMemoryDatabaseBuilder(
//                context,
//                ShopDataBase::class.java,
//            )
//            .fallbackToDestructiveMigration()
//            .build()

        return Room.databaseBuilder(
            context.applicationContext,
            ShopDataBase::class.java,
            Constants.SHOP_DATABASE
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideShopdDao(shopDataBase: ShopDataBase): ShopDao = shopDataBase.shopDao()
}