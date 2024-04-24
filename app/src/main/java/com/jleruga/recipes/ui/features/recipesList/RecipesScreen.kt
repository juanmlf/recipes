package com.jleruga.recipes.ui.features.recipesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jleruga.recipes.R
import com.jleruga.recipes.domain.model.RecipeDomain
import com.jleruga.recipes.ui.components.RecipeCard
import com.jleruga.recipes.ui.theme.DarkestGreen
import com.jleruga.recipes.ui.theme.RecipesTheme
import com.jleruga.recipes.ui.util.getMockedRecipeList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@Composable
fun RecipesScreen(
    scope: CoroutineScope,
    viewModel: RecipesListViewModel,
    paddingValues: PaddingValues
) {
    val searchValue by viewModel.searchValue
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(DarkestGreen)
    ) {
        RecipesContent(recipeDomains = state.recipeDomains, searchValue) {
            viewModel.getRecipesByName(it.text)
        }
    }
}

@Composable
fun RecipesContent(
    recipeDomains: List<RecipeDomain>,
    searchValue: String,
    onValueChange: (TextFieldValue) -> Unit
) {
    val fieldValue = remember {
        mutableStateOf(TextFieldValue(text = searchValue))
    }
    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .focusRequester(focusRequester),
            value = fieldValue.value,
            trailingIcon = {
                IconButton(onClick = { onValueChange.invoke(fieldValue.value) }) {
                    Icon(Icons.Sharp.Search, contentDescription = "Search Icon")
                }
            },
            onValueChange = {
                onValueChange.invoke(fieldValue.value)
                fieldValue.value = it
            },
            shape = CutCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = RecipesTheme.colors.secondary,
                unfocusedBorderColor = RecipesTheme.colors.primaryContainer,
                cursorColor = RecipesTheme.colors.secondary,
                focusedContainerColor = RecipesTheme.colors.primary,
                unfocusedContainerColor = RecipesTheme.colors.primary,
                unfocusedTrailingIconColor = RecipesTheme.colors.primaryContainer,
                focusedTrailingIconColor = RecipesTheme.colors.secondary,
                unfocusedLabelColor = RecipesTheme.colors.secondary,
                focusedTextColor = RecipesTheme.colors.secondary,
                unfocusedTextColor = RecipesTheme.colors.onPrimary
            )
        )
        if (recipeDomains.isNotEmpty())
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 180.dp),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                items(recipeDomains) { recipe ->
                    RecipeCard(recipe)
                }
            }
        else
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    painter = painterResource(id = R.drawable.error_not_available),
                    contentDescription = "Not available icon",
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    fontSize = 22.sp,
                    text = "Oh, oh!"
                )
            }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipesScreenPreview() {
    RecipesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp)
        ) {
            val recipes = getMockedRecipeList()
            RecipesContent(recipeDomains = recipes, "soup", {})
        }
    }
}