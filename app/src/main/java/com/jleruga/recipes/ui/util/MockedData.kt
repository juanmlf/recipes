package com.jleruga.recipes.ui.util

import com.jleruga.recipes.domain.model.RecipeDomain
import java.util.UUID

fun getMockedRecipe(): RecipeDomain{
    return RecipeDomain(
        id = UUID.randomUUID().toString(),
        name = "Sopa de nabo",
        ingredients = listOf("Ingrediente 1", "Ingrediente 2", "Ingrediente 3"),
        instructions = "Lorem ipsum",
        image = "",
        category = "Sopas",
        area = "Almuerzos",
        date = "12/02/2024"
    )
}

fun getMockedRecipeList(): List<RecipeDomain>{
    return listOf(
        RecipeDomain(
        id = UUID.randomUUID().toString(),
        name = "Sopa de nabo",
        ingredients = listOf("", "", ""),
        instructions = "Lorem ipsum",
        image = "",
        category = "Sopas",
        area = "Almuerzos",
        date = "12/02/2024"
    ),
        RecipeDomain(
            id = UUID.randomUUID().toString(),
            name = "Sopa de nabo",
            ingredients = listOf("", "", ""),
            instructions = "Lorem ipsum",
            image = "",
            category = "Sopas",
            area = "Almuerzos",
            date = "12/02/2024"
        ),
        RecipeDomain(
            id = UUID.randomUUID().toString(),
            name = "Sopa de nabo",
            ingredients = listOf("", "", ""),
            instructions = "Lorem ipsum",
            image = "",
            category = "Sopas",
            area = "Almuerzos",
            date = "12/02/2024"
        ),
        RecipeDomain(
            id = UUID.randomUUID().toString(),
            name = "Sopa de nabo",
            ingredients = listOf("", "", ""),
            instructions = "Lorem ipsum",
            image = "",
            category = "Sopas",
            area = "Almuerzos",
            date = "12/02/2024"
        ))
}