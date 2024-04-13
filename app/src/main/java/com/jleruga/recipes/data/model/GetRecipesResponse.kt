package com.jleruga.recipes.data.model

import com.jleruga.recipes.domain.model.Recipe

interface GetRecipesResponse {
    fun getRecipes(): List<Any>
}

data class RecipesResult(
    val recipe: com.jleruga.recipes.data.model.Recipe
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
        return mutableListOf<com.jleruga.recipes.data.model.Recipe>().apply {
            hits.forEach { hit ->
                this.add(hit.recipe) }
        }
    }
}