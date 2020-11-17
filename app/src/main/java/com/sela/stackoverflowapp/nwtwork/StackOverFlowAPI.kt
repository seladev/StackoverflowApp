package com.sela.stackoverflowapp.nwtwork

import retrofit2.http.GET
import retrofit2.http.Query


/**
 * StackOverFlow Api - for get StackOverFlow objects from StackOverFlow api
 */
interface StackOverFlowAPI {

    /**
     * get questions main page
     */
    @GET("questions?order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestionsData(@Query("page") page: Int): StackOverFlowResponse

}