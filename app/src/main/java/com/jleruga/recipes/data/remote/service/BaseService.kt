package com.jleruga.recipes.data.remote.service

import retrofit2.Response

interface BaseService<T> {
    suspend fun getRecipesByName(name: String) : Response<T>
}