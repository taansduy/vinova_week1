package com.example.asus.week1.api

import com.example.asus.week1.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient

class api {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val LANGUAGE = "vi"

        private fun builder(): Retrofit {
            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client())
                .build()
        }

        private fun client(): OkHttpClient {
            return OkHttpClient.Builder().addNetworkInterceptor { chain ->
                var request = chain.request()
                val url = request.url().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
//                    .addQueryParameter("language", LANGUAGE)
                    .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }.build()
        }

        fun createService(): TheMovieDatabaseApi {
            return builder().create(TheMovieDatabaseApi::class.java)
        }
    }
}