package com.jleruga.recipes.data.remote.service

import com.jleruga.recipes.data.model.GetRecipesResponse
import retrofit2.Response

interface BaseService<T> {
    suspend fun getRecipesByName(name: String) : Response<T>
}