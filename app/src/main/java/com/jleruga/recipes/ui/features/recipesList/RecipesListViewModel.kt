package com.jleruga.recipes.ui.features.recipesList

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jleruga.recipes.domain.GetRecipesByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(private val getRecipesByNameUseCase: GetRecipesByNameUseCase) :
    ViewModel() {
    private val _state =
        MutableStateFlow<RecipesListUiState>(RecipesListUiState.Empty)
    val state: StateFlow<RecipesListUiState> = _state

    var searchValue = mutableStateOf("soup")

    init {
        viewModelScope.launch {
            when (state.value) {
                is RecipesListUiState.Empty -> {
                    getRecipesByName(searchValue.value)
                }

                else -> {}
            }
        }
    }

    fun getRecipesByName(searchValue: String) {
        viewModelScope.launch {
            val result = getRecipesByNameUseCase(searchValue)
            if (result.isSuccessful)
                result.body()?.let { list ->
                    _state.update {
                        RecipesListUiState.GetRecipesSuccessfully(list)
                    }
                }
            else {
                _state.update {
                    RecipesListUiState.GetRecipesError(result.errorBody().toString())
                }
            }
        }
    }
}