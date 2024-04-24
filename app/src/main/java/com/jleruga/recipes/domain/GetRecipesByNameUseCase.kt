package com.jleruga.recipes.domain

import com.jleruga.recipes.domain.model.RecipeDomain
import retrofit2.Response
import javax.inject.Inject

class GetRecipesByNameUseCase @Inject constructor(private val repository: RecipesRepository){
    suspend operator fun invoke(name: String): Response<List<RecipeDomain>> {
        return repository.getRecipesByName(name)
    }
}