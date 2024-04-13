package com.jleruga.recipes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jleruga.recipes.R
import com.jleruga.recipes.ui.features.recipesList.RecipesListViewModel
import com.jleruga.recipes.ui.features.recipesList.RecipesScreen
import com.jleruga.recipes.ui.theme.RecipesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            val viewModel = hiltViewModel<RecipesListViewModel>()

            RecipesTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = RecipesTheme.colors.primaryContainer,
                                titleContentColor = RecipesTheme.colors.primary,
                            ),
                            title = {
                                Text(stringResource(R.string.app_title))
                            }
                        )
                    }, bottomBar = {}, content = {
                        RecipesScreen(scope = scope, viewModel = viewModel, it)
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipesTheme {
        Text(text = "Hello")
    }
}