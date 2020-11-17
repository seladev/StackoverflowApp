package com.sela.stackoverflowapp.ui.feed

import com.sela.stackoverflowapp.data.model.QuestionModel

/**
 * Created by seladev
 */
interface RecyclerViewClickListener {
    fun onClick(item:QuestionModel)
}