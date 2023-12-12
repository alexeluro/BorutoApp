package com.inspiredcoda.boruto.di

import android.content.Context
import androidx.room.Room
import com.inspiredcoda.boruto.constants.Constants
import com.inspiredcoda.boruto.constants.Constants.Database.DATABASE_NAME
import com.inspiredcoda.boruto.data.local.BorutoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideBorutoDatabase(
        @ApplicationContext context: Context
    ): BorutoDatabase {
        return Room.databaseBuilder(
            context,
            BorutoDatabase::class.java,
            DATABASE_NAME
        ).build()
    }


}