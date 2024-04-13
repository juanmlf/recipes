package com.jleruga.recipes

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jleruga.recipes.data.local.RecipesDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipesApplication: Application()