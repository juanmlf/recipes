package com.jleruga.recipes.di

import com.jleruga.recipes.data.remote.RecipesRemoteDataSource
import com.jleruga.recipes.data.RecipesRepositoryImpl
import com.jleruga.recipes.data.local.RecipesDatabase
import com.jleruga.recipes.data.local.RecipesLocalDataSource
import com.jleruga.recipes.data.remote.service.RecipesEdamamService
import com.jleruga.recipes.data.remote.service.RecipesServiceProvider
import com.jleruga.recipes.data.remote.utils.RetrofitUtil
import com.jleruga.recipes.domain.GetRecipesByNameUseCase
import com.jleruga.recipes.domain.RecipesRepository
import com.jleruga.recipes.ui.features.recipesList.RecipesListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipesModule {
    @Provides
    fun provideRecipesListViewModel(getRecipesByNameUseCase: GetRecipesByNameUseCase): RecipesListViewModel {
        return RecipesListViewModel(getRecipesByNameUseCase)
    }

    @Provides
    fun provideGetRecipesByNameUseCase(repository: RecipesRepository): GetRecipesByNameUseCase {
        return GetRecipesByNameUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRecipesRepository(
        localDataSource: RecipesLocalDataSource,
        remoteDataSource: RecipesRemoteDataSource
    ): RecipesRepository {
        return RecipesRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideRecipesLocalDataSource(recipesDatabase: RecipesDatabase) : RecipesLocalDataSource {
        return RecipesLocalDataSource(recipesDatabase.recipesDao())
    }

    @Provides
    @Singleton
    fun provideRecipesRemoteDataSource(serviceProvider: RecipesServiceProvider) : RecipesRemoteDataSource {
        return RecipesRemoteDataSource(serviceProvider)
    }

    @Provides
    @Singleton
    fun provideRecipesServiceProvider(): RecipesServiceProvider{
        return RecipesServiceProvider()
    }

    @Provides
    @Singleton
    fun provideRecipesService(serviceProvider: RecipesServiceProvider): Any {
        return serviceProvider.getRecipesService()
    }

    @Provides
    @Singleton
    fun provideRecipesEdamamService(): RecipesEdamamService {
        return RetrofitUtil.createRecipesEdamamService()
    }
}