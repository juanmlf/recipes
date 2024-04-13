package com.jleruga.recipes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jleruga.recipes.R
import com.jleruga.recipes.domain.model.Recipe
import com.jleruga.recipes.ui.theme.RecipeTypography
import com.jleruga.recipes.ui.theme.RecipesTheme
import com.jleruga.recipes.ui.theme.Roboto
import com.jleruga.recipes.ui.util.getMockedRecipe
import java.time.format.TextStyle

@Composable
fun RecipeCard(
    recipe: Recipe,
    backgroundCardColor: Color = RecipesTheme.colors.primaryContainer,
    textColor: Color = RecipesTheme.colors.primary
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .height(250.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = backgroundCardColor,
            contentColor = textColor
        ),
        shape = CutCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        AsyncImage(
            modifier = Modifier
                .weight(0.5F)
                .border(width = 8.dp, color = Color.Transparent, shape = CutCornerShape(8.dp)),
            model = recipe.image,
            contentDescription = recipe.name,
            contentScale = ContentScale.FillWidth,
            clipToBounds = true,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .weight(0.5F)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Name: ${recipe.name.toString()}",
                fontStyle = RecipeTypography.bodyLarge.fontStyle,
                fontSize = 18.sp
            )
//            Text(
//                modifier = Modifier.padding(bottom = 4.dp),
//                text = "Ingredients:",
//                fontWeight = FontWeight.Bold
//            )
//            Column(modifier = Modifier.height(20.dp)) {
//                recipe.ingredients?.forEach { ingredient ->
//                    ListItem(headlineContent = { Text(text = ingredient) })
//                }
//            }
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = "Category: ${recipe.category}",
                fontWeight = FontWeight.Medium,
                fontFamily = RecipeTypography.bodyMedium.fontFamily,
                fontSize = 14.sp
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = "Area: ${recipe.area}",
                fontWeight = FontWeight.Medium,
                fontFamily = RecipeTypography.bodyMedium.fontFamily,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    RecipesTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            RecipeCard(recipe = getMockedRecipe())
            Spacer(modifier = Modifier.height(4.dp))
            RecipeCard(recipe = getMockedRecipe())
            Spacer(modifier = Modifier.height(4.dp))
            RecipeCard(recipe = getMockedRecipe())
            Spacer(modifier = Modifier.height(4.dp))
            RecipeCard(recipe = getMockedRecipe())
        }
    }
}