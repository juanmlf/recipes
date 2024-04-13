package com.jleruga.recipes.data.local

import com.jleruga.recipes.data.local.model.RecipeDao
import com.jleruga.recipes.data.local.model.RecipeEntity
import com.jleruga.recipes.domain.model.Recipe
import com.jleruga.recipes.domain.model.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipesLocalDataSource(private val recipeDao: RecipeDao) {
    suspend fun getRecipesByName(name: String): List<RecipeEntity> {
        return withContext(Dispatchers.IO) { recipeDao.getRecipesByName(name) }
    }

    suspend fun saveRecipe(recipe: Recipe) {
        return withContext(Dispatchers.IO) {
            if (recipeDao.getRecipeById(recipe.id) == null)
                recipeDao.saveRecipe(recipe.toModel())
        }
    }
}