package com.jleruga.recipes.data.remote.service

import com.jleruga.recipes.data.remote.response.GetRecipesResult
import com.jleruga.recipes.data.remote.utils.RetrofitUtil
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesService {
    @GET("search.php?s=")
    suspend fun getRecipesByName(
        @Query("s") name: String
    ) : Response<GetRecipesResult>

    companion object {
        fun create(): RecipesService {
            return RetrofitUtil.createRecipesService()
        }
    }

}