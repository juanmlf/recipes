package com.jleruga.recipes.data.mapper

import com.jleruga.recipes.data.local.model.RecipeEntity
import com.jleruga.recipes.data.remote.model.Meal
import com.jleruga.recipes.domain.model.RecipeDomain
import com.jleruga.recipes.data.remote.model.Recipe as RecipeData

fun Meal.toDomain(): RecipeDomain {
    return RecipeDomain(
        id = idMeal,
        name = strMeal,
        ingredients = getIngredientsList(this),
        instructions = strInstructions,
        image = strMealThumb,
        category = strCategory,
        area = strArea,
        date = dateModified?.toString() ?: ""
    )
}

fun RecipeData.toDomain() : RecipeDomain {
    return RecipeDomain(
        id = uri.split("recipe_")[1],
        name = label,
        ingredients = ingredientLines,
        instructions = null,
        image = image,
        category = dishType?.first().orEmpty(),
        area = cuisineType?.first().orEmpty(),
        date = null
    )
}

private fun getIngredientsList(meal: Meal?): List<String> {
    val list = mutableListOf<String>()
    if (meal != null) {
        var index = 1
        var keepGoing = true
        while (index <= 20 && keepGoing) {
            if (!(meal["strIngredient$index"] as String?).isNullOrEmpty()) {
                list.add(meal["strIngredient$index"] as String + " - " + meal["strMeasure$index"] as String)
                index++
            } else {
                keepGoing = false
            }
        }
    }
    return list
}

fun RecipeEntity.toDomain(): RecipeDomain {
    return RecipeDomain(
        id = id,
        name = name,
        ingredients = ingredients,
        instructions = instructions,
        image = image,
        category = category,
        area = area,
        date = date
    )
}

fun RecipeDomain.toModel(): RecipeEntity {
    return RecipeEntity(
        id = id,
        name = name,
        ingredients = ingredients,
        instructions = instructions,
        image = image,
        category = category,
        area = area,
        date = date
    )
}