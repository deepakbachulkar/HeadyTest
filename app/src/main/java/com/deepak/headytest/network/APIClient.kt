package com.deepak.headytest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {
    companion object {
        val baseURL: String = APIConstant.BASE_URL;
        var retofit: Retrofit? = null

        val client: Retrofit
            get() {
                if (retofit == null) {
                    val interceptor = HttpLoggingInterceptor()
                    val client = OkHttpClient.Builder().addInterceptor(interceptor)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .build()
                    retofit = Retrofit.Builder()
                            .baseUrl(baseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build()
                }
                return retofit!!
            }
    }


}
