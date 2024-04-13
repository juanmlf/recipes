package com.jleruga.recipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jleruga.recipes.data.local.model.RecipeDao
import com.jleruga.recipes.data.local.model.RecipeEntity
import com.jleruga.recipes.data.local.util.IngredientsConverter

@TypeConverters(IngredientsConverter::class)
@Database (
    entities = [RecipeEntity::class],
    version = 1
)
abstract class RecipesDatabase : RoomDatabase() {
   abstract fun recipesDao() : RecipeDao
}