package com.example.tbcrevision8.di

import android.content.Context
import com.example.tbcrevision8.data.network.RandomApi
import com.example.tbcrevision8.data.remote.client.OkHttpClient
import com.example.tbcrevision8.data.repository.RandomRepositoryImpl
import com.example.tbcrevision8.domain.repository.RandomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.getInstance(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideRandomApi(retrofit: Retrofit): RandomApi {
        return retrofit.create(RandomApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJokesRepository(randomApi: RandomApi): RandomRepository {
        return RandomRepositoryImpl(randomApi)
    }

}