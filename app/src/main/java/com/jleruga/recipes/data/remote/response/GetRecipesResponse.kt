package com.jleruga.recipes.data.remote.response

import com.jleruga.recipes.data.remote.model.Meal
import com.jleruga.recipes.data.remote.model.Recipe

interface GetRecipesResponse {
    fun getRecipes(): List<Any>
}

data class RecipesResult(
    val recipe: Recipe
)

data class GetRecipesResult(
    val meals: List<Meal>
): GetRecipesResponse {
    override fun getRecipes(): List<Any> {
        return meals
    }
}

data class GetRecipesEdamamResult (
    val hits: List<RecipesResult>
) : GetRecipesResponse {
    override fun getRecipes(): List<Any> {
        return mutableListOf<Recipe>().apply {
            hits.forEach { hit ->
                this.add(hit.recipe) }
        }
    }
}