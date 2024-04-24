package com.jleruga.recipes.data.remote.service

import com.jleruga.recipes.data.remote.response.GetRecipesEdamamResult
import com.jleruga.recipes.data.remote.utils.ApiConstants
import com.jleruga.recipes.data.remote.utils.RetrofitUtil
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesEdamamService{
    @GET(ApiConstants.pathEdamamBase)
    suspend fun getRecipesByName(
        @Query("q") name: String
        ) : Response<GetRecipesEdamamResult>

    companion object {
        fun create(): RecipesEdamamService {
            return RetrofitUtil.createRecipesEdamamService()
        }
    }
}