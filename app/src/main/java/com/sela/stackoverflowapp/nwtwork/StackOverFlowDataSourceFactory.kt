package com.sela.stackoverflowapp.nwtwork

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.sela.stackoverflowapp.data.model.QuestionModel
import kotlinx.coroutines.CoroutineScope


/**
 * Created by seladev
 */
class StackOverFlowDataSourceFactory(private val scope: CoroutineScope) : DataSource.Factory<Int, QuestionModel>() {

    val questionsLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, QuestionModel>>()
    var questionsDataSource: StackOverFlowDataSource? = null

    override fun create(): DataSource<Int, QuestionModel> {
        questionsDataSource = StackOverFlowDataSource(scope)
        questionsLiveDataSource.postValue(questionsDataSource)
        return questionsDataSource!!
    }


    fun invalidate(){
        StackOverFlowDataSource(scope).invalidate()
    }
}