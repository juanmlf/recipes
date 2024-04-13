package com.jleruga.recipes.domain

import com.jleruga.recipes.data.RecipesRepositoryImpl
import com.jleruga.recipes.domain.model.Recipe
import retrofit2.Response
import javax.inject.Inject

class GetRecipesByNameUseCase @Inject constructor(private val repository: RecipesRepository){
    suspend operator fun invoke(name: String): Response<List<Recipe>> {
        return repository.getRecipesByName(name)
    }
}