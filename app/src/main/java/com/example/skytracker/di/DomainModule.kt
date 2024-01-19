package com.example.rick_and_morty_characters_wiki.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

//    @Singleton
//    @Provides
//    fun provideLoadCharactersUseCase(charactersRepository: CharactersRepository) : LoadCharactersUseCase {
//        return LoadCharactersUseCase(charactersRepository)
//    }
//
//    @Singleton
//    @Provides
//    fun provideLoadImageUseCase() : LoadImageUseCase {
//        return LoadImageUseCase()
//    }

}