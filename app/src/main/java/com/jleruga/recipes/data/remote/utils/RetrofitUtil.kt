package com.jleruga.recipes.data.remote.utils

import com.jleruga.recipes.data.remote.service.BaseService
import com.jleruga.recipes.data.remote.service.RecipesEdamamService
import com.jleruga.recipes.data.remote.service.RecipesService
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

enum class BaseUrlApi(val value: String) {
    THEMEALDB("https://www.themealdb.com/api/json/v1/1"),
    EDAMAM("https://api.edamam.com/api/recipes/")
}

object RetrofitUtil: RetrofitProvider {
    var baseUrl = BaseUrlApi.EDAMAM

    override fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl.value.toHttpUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    inline fun <reified T> createRecipesService(): T {
//        return when(baseUrl){
//            BaseUrlApi.THEMEALDB -> {
//                getRetrofit().create(RecipesService::class.java) as T
//            }
//            BaseUrlApi.EDAMAM -> {
//                getRetrofit().create(RecipesEdamamService::class.java) as T
//            }
//        }
//    }

    fun createRecipesService(): RecipesService {
        return getRetrofit().create(RecipesService::class.java)
    }

    fun createRecipesEdamamService(): RecipesEdamamService {
        return getRetrofit().create(RecipesEdamamService::class.java)
    }

}

interface RetrofitProvider {
    fun getRetrofit(): Retrofit
}
