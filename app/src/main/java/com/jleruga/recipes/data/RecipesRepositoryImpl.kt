package com.jleruga.recipes.data

import com.jleruga.recipes.data.remote.RecipesRemoteDataSource
import com.jleruga.recipes.data.local.RecipesLocalDataSource
import com.jleruga.recipes.data.model.Meal
import com.jleruga.recipes.domain.RecipesRepository
import com.jleruga.recipes.domain.model.Recipe as RecipeDomain
import com.jleruga.recipes.data.model.Recipe as RecipeData
import com.jleruga.recipes.domain.model.toDomain
import retrofit2.Response
import java.util.zip.DataFormatException
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RecipesRemoteDataSource,
    private val localDataSource: RecipesLocalDataSource
): RecipesRepository {

    override suspend fun getRecipesByName(name: String): Response<List<RecipeDomain>> {
        val list = mutableListOf<RecipeDomain>()
        val localResult = localDataSource.getRecipesByName(name)
        if(localResult.isNotEmpty()) {
            localResult.forEach { list.add(it.toDomain()) }
        }else {
            val remoteResult = remoteDataSource.getRecipesByName(name)
            if (remoteResult.isSuccessful)
                remoteResult.body()?.forEach {
                    val recipe = mapRecipeData(it)
                    localDataSource.saveRecipe(recipe)
                    list.add(recipe)
                }
            else
                return Response.error(remoteResult.code(), remoteResult.errorBody())
        }
        return Response.success(list)
    }
}

private fun mapRecipeData(recipe: Any): RecipeDomain{
    return when(recipe){
        is RecipeData -> {
            recipe.toDomain()
        }
        is Meal -> {
            recipe.toDomain()
        }
        else -> {
            throw DataFormatException("No recognized recipe model")
        }
    }
}