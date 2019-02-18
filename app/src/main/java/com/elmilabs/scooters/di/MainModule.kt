package com.elmilabs.scooters.di

import com.elmilabs.scooters.BuildConfig
import com.elmilabs.scooters.scooterListing.model.RestClient
import com.elmilabs.scooters.scooterListing.model.RetrofitScooterDataSource
import com.elmilabs.scooters.scooterListing.model.repository.ScooterRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    internal fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.BASIC
                )
            )
            .build()
    }

    @Provides
    @Singleton
    internal fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
    }

    @Provides
    @Singleton
    internal fun providesRestClient(builder: Retrofit.Builder): RestClient {
        return builder
            .baseUrl("https://qc05n0gp78.execute-api.eu-central-1.amazonaws.com/")
            .build()
            .create(RestClient::class.java)
    }



    @Provides
    @Singleton
    internal fun providesRetrofitScooterDataSource(restClient: RestClient): RetrofitScooterDataSource {
        return RetrofitScooterDataSource(restClient)
    }

    @Provides
    @Singleton
    internal fun providesScooterRepository(retrofitScooterDataSource: RetrofitScooterDataSource): ScooterRepository {
        return ScooterRepository(retrofitScooterDataSource)
    }


}