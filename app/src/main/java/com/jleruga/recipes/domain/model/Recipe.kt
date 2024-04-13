package com.jleruga.recipes.domain.model

data class Recipe (
    var id: String,
    var name: String? = null,
    var ingredients: List<String>? = null,
    var instructions: String? = null,
    var image: String? = null,
    var category: String? = null,
    var area: String? = null,
    var date: String? = null
)