package com.example.projectmaps.data.modules

import android.app.Application
import android.content.Context
import com.example.projectmaps.data.local.AppDatabase
import com.example.projectmaps.data.repository.RouteRepository
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
    fun provideRouteRepository(database: AppDatabase): RouteRepository {
        return RouteRepository(database.routeDao())
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideRouteDao(database: AppDatabase) = database.routeDao()
}