package com.jleruga.recipes.data.remote.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("calories")
    val calories: Double,
    @SerializedName("cuisineType")
    val cuisineType: List<String?>?,
    @SerializedName("dishType")
    val dishType: List<String?>?,
    @SerializedName("image")
    val image: String,
    @SerializedName("ingredientLines")
    val ingredientLines: List<String>,
    @SerializedName("label")
    val label: String,
)