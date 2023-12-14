package com.example.useralbumviewer.features.user_album.di

import com.example.useralbumviewer.features.user_album.data.remote_source.AlbumsInterface
import com.example.useralbumviewer.features.user_album.data.repository.RepositoryImpl
import com.example.useralbumviewer.features.user_album.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance():Retrofit{

        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

    }

    @Provides
    @Singleton
    fun provideAlbumInterfaceInstance(retrofit: Retrofit):AlbumsInterface{

        return retrofit.create(AlbumsInterface::class.java)

    }


}

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule{

    @Binds
    @Singleton
    abstract fun bindsRepo(repositoryImpl: RepositoryImpl):Repository


}