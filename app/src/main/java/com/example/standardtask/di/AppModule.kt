package com.example.standardtask.di

import android.content.Context
import com.example.standardtask.network.RetrofitInterface
import com.example.standardtask.network.RetrofitObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Retrofit Module
    @Singleton
    @Provides
    fun providesRetrofit(): RetrofitInterface {
        return RetrofitObject.retrofit
    }

    // Dispatchers.IO
    @Singleton
    @Provides
    fun provideIoDispatcher( ): CoroutineDispatcher {
        return Dispatchers.IO
    }

    // Context
    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}