package com.jleruga.recipes.data.local.util

import androidx.room.TypeConverter
import com.google.gson.Gson

class IngredientsConverter {
    companion object {
        const val SEPARATOR = ","
    }

    @TypeConverter
    fun fromList(ingredients: List<String>?): String {
        return Gson().toJson(ingredients)
    }

    @TypeConverter
    fun toList(ingredients: String) : List<String>{
        return if(ingredients.isNotEmpty())
            Gson().fromJson<List<String>>(ingredients, List::class.java) ?: emptyList()
        else
            emptyList()
    }
}