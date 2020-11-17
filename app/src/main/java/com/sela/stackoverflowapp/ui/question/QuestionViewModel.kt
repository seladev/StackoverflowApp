package com.sela.stackoverflowapp.ui.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sela.stackoverflowapp.data.model.QuestionModel

/**
 * Created by seladev
 */
class QuestionViewModel : ViewModel() {


    private var _questionLink = MutableLiveData<String>()
    val questionLink : LiveData<String>
        get() = _questionLink


    fun setQuestionLink(link:String?){
        _questionLink.value = link
    }
}