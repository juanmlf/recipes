package com.jleruga.recipes.di

import android.app.Application
import androidx.room.Room
import com.jleruga.recipes.data.local.RecipesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application) : RecipesDatabase {
        return Room.databaseBuilder(app.applicationContext, RecipesDatabase::class.java, "recipes").build()
    }
}