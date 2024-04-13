package com.jleruga.recipes.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String?,
    val ingredients: List<String>?,
    val instructions: String?,
    val image: String?,
    val category: String?,
    val area: String?,
    val date: String?
)
