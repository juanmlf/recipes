package com.jleruga.recipes.data.local.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecipeDao {
    @Query("SELECT * FROM RecipeEntity")
    fun getAll() : List<RecipeEntity>

    @Query("SELECT * FROM RecipeEntity WHERE :name == name")
    fun getRecipesByName(name: String) : List<RecipeEntity>


    @Query("SELECT * FROM RecipeEntity WHERE :id == id")
    fun getRecipeById(id: String) : RecipeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRecipe(recipeEntity: RecipeEntity)

    @Update
    fun updateRecipe(recipeEntity: RecipeEntity)
}