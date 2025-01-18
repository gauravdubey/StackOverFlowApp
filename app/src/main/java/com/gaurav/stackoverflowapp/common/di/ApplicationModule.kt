package com.gaurav.stackoverflowapp.common.di

import android.app.Application
import androidx.room.Room
import com.gaurav.stackoverflowapp.BuildConfig
import com.gaurav.stackoverflowapp.common.Constants
import com.gaurav.stackoverflowapp.common.database.MyRoomDatabase
import com.gaurav.stackoverflowapp.networking.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder().run {
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }

            })
            build()
        }
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMyRoomDatabase(application: Application): MyRoomDatabase {
        return Room.databaseBuilder(
            application,
            MyRoomDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }

    @Provides
    fun provideFavoriteQuestionDao(myRoomDatabase: MyRoomDatabase) =
        myRoomDatabase.favoriteQuestionDao
}