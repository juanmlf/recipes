package com.jleruga.recipes.domain

import com.jleruga.recipes.domain.model.Recipe
import retrofit2.Response

interface RecipesRepository{
    suspend fun getRecipesByName(name: String): Response<List<Recipe>>
}