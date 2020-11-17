package com.sela.stackoverflowapp.nwtwork

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 *  StackOverFlow Retrofit Service - make RestApi call by Retrofit
 */
object StackOverFlowRetrofitService {

    private const val BASE_URL = "https://api.stackexchange.com/2.2/"

    /**
     * Http log - when making  request show the request in logcat
     */
    private val logInterceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }


    private val client = OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor { chain ->
                val original = chain.request()
                val url = original.url.newBuilder().build()
                val request  = original.newBuilder().url(url).build()
                return@Interceptor chain.proceed(request)
            })
        addInterceptor(logInterceptor)
    }.build()


    /**
     * Retrofit
     */
    private  val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
    .build()

    /**
     * Build the service
     */
    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }


}
