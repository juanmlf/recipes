package com.jleruga.recipes.data.remote

import com.jleruga.recipes.data.remote.service.RecipesEdamamService
import com.jleruga.recipes.data.remote.service.RecipesService
import com.jleruga.recipes.data.remote.service.RecipesServiceProvider
import com.jleruga.recipes.data.remote.utils.EmptyResponseBody
import okhttp3.ResponseBody
import retrofit2.Response

class RecipesRemoteDataSource (private val serviceProvider: RecipesServiceProvider){
    suspend fun getRecipesByName(name: String): Response<List<Any>> {
        when(val service = serviceProvider.getRecipesService()){
            is RecipesService -> {
                val result = service.getRecipesByName(name)
                return if(result.isSuccessful)
                    Response.success(result.body()?.getRecipes() ?: emptyList())
                else
                    Response.error(result.code(), result.errorBody() ?: EmptyResponseBody())
            }
            is RecipesEdamamService -> {
                val result = service.getRecipesByName(name)
                return if(result.isSuccessful)
                    Response.success(result.body()?.getRecipes() ?: emptyList())
                else
                    Response.error(result.code(), result.errorBody() ?: EmptyResponseBody())
            }
            else -> throw IllegalArgumentException("Unsupported service type")
        }
    }
}