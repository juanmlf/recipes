package com.jleruga.recipes.ui.features.recipesList

import com.jleruga.recipes.domain.model.Recipe

sealed class RecipesListUiState (
    private val loading: Boolean = false,
    val recipes: List<Recipe> = mutableListOf()
){
    data object Empty : RecipesListUiState()
    class GetRecipesSuccessfully(recipes: List<Recipe>) : RecipesListUiState(loading = false, recipes)
    class GetRecipesError(error: String) : RecipesListUiState(loading = false)
}