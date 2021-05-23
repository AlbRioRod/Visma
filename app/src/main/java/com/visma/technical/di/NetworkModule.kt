package com.visma.technical.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.visma.technical.BuildConfig
import com.visma.technical.data.remote.LocalWeatherService
import com.visma.technical.data.remote.ProvincesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesGsonServer(): Gson = GsonBuilder().create()

    @Provides
    @Named("okHttpClient")
    fun providesOkHttpClientBuilder(): OkHttpClient.Builder =
        HttpLoggingInterceptor().run {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

            OkHttpClient.Builder()
                .addInterceptor(this)
        }

    @Provides
    @Named("currentWeather")
    fun provideRetrofitCurrent(
        @Named("okHttpClient") okHttpClientBuilder: OkHttpClient.Builder,
        gson: Gson
    ) = Retrofit.Builder().baseUrl("http://api.worldweatheronline.com/")
        .client(okHttpClientBuilder.build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideCurrentWeatherService(@Named("currentWeather") retrofit: Retrofit): LocalWeatherService =
        retrofit.create(LocalWeatherService::class.java)


    @Provides
    @Named("provinces")
    fun provideRetrofitProvinces(
        @Named("okHttpClient") okHttpClientBuilder: OkHttpClient.Builder,
        gson: Gson
    ) = Retrofit.Builder().baseUrl("https://www.el-tiempo.net/api/json/v2/")
        .client(okHttpClientBuilder.build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideProvincesWeatherService(@Named("provinces") retrofit: Retrofit): ProvincesService =
        retrofit.create(ProvincesService::class.java)
}