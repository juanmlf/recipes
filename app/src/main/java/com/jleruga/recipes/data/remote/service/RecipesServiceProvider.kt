package com.jleruga.recipes.data.remote.service

import com.jleruga.recipes.data.model.GetRecipesResponse
import com.jleruga.recipes.data.remote.utils.BaseUrlApi
import com.jleruga.recipes.data.remote.utils.RetrofitUtil

class RecipesServiceProvider {
    fun getRecipesService(): Any {
        return when (RetrofitUtil.baseUrl) {
            BaseUrlApi.THEMEALDB -> RetrofitUtil.createRecipesService()
            BaseUrlApi.EDAMAM -> RetrofitUtil.createRecipesEdamamService()
        }
    }
}