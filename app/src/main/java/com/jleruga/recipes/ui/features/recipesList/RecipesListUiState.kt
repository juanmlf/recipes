package com.jleruga.recipes.ui.features.recipesList

import com.jleruga.recipes.domain.model.RecipeDomain

sealed class RecipesListUiState (
    private val loading: Boolean = false,
    val recipeDomains: List<RecipeDomain> = mutableListOf()
){
    data object Empty : RecipesListUiState()
    class GetRecipesSuccessfully(recipeDomains: List<RecipeDomain>) : RecipesListUiState(loading = false, recipeDomains)
    class GetRecipesError(error: String) : RecipesListUiState(loading = false)
}