package com.sela.stackoverflowapp.ui.feed

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sela.stackoverflowapp.data.model.Filter
import com.sela.stackoverflowapp.data.model.QuestionModel
import com.sela.stackoverflowapp.nwtwork.StackOverFlowDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


class FeedViewModel(application: Application) : AndroidViewModel(application){

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val pageSize = 10

    var questionList: LiveData<PagedList<QuestionModel>>

    private var stackOverFlowDataSourceFactory:StackOverFlowDataSourceFactory

    init {
        stackOverFlowDataSourceFactory = StackOverFlowDataSourceFactory(uiScope)

        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(true)
            .build()

        questionList = LivePagedListBuilder(stackOverFlowDataSourceFactory, pagedListConfig).build()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun setFilter(filter: Filter) {
        //TODO
    }

    fun refresh() {
        stackOverFlowDataSourceFactory.questionsLiveDataSource.value?.invalidate()
    }

}
