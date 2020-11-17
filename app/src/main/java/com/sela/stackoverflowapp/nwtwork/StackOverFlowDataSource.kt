package com.sela.stackoverflowapp.nwtwork

import androidx.paging.PageKeyedDataSource
import com.sela.stackoverflowapp.data.model.QuestionModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * Created by seladev
 */
class StackOverFlowDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<Int, QuestionModel>() {

    companion object{
        //we will start from the first page which is 1
        private const val FIRST_PAGE = 1

    }

    private val stackOverFlowAPI =  StackOverFlowRetrofitService.buildService(StackOverFlowAPI::class.java)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, QuestionModel>) {
        scope.launch {
            try {
                val stackOverFlowResponse = stackOverFlowAPI.getQuestionsData(FIRST_PAGE)
                callback.onResult(stackOverFlowResponse.items, null, FIRST_PAGE + 1)
            }catch (e: Exception){
                callback.onResult(emptyList(), null, 0)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, QuestionModel>) {
        scope.launch {
            try {
                val stackOverFlowResponse = stackOverFlowAPI.getQuestionsData(params.key)
                val beforePage = if(params.key>1) params.key-1 else 0
                callback.onResult(stackOverFlowResponse.items, beforePage)
            }catch (e: Exception){
                callback.onResult(emptyList(), null)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, QuestionModel>) {
        scope.launch {
            try {
                val stackOverFlowResponse = stackOverFlowAPI.getQuestionsData(params.key)
                val afterPage = if(stackOverFlowResponse.hasMore) params.key+1 else null
                callback.onResult(stackOverFlowResponse.items, afterPage)
            }catch (e: Exception){
                callback.onResult(emptyList(), null)
            }
        }
    }


}