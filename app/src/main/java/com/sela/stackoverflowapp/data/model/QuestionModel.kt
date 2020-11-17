package com.sela.stackoverflowapp.data.model

import com.google.gson.annotations.SerializedName


/**
 * Created by seladev
 */
data class QuestionModel(@SerializedName("question_id") var questionId:String,
                         @SerializedName("title") var title:String,
                         @SerializedName("link") var link:String,
                         @SerializedName("creation_date") var creationDate:Long,
                         @SerializedName("answer_count") var answerCount:Int,
                         @SerializedName("score") var score:Int,
                         @SerializedName("is_answered") var isAnswered:Boolean,
                         @SerializedName("owner") var owner:OwnerModel
) {

}