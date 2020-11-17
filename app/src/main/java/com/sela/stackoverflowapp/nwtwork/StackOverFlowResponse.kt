package com.sela.stackoverflowapp.nwtwork

import com.google.gson.annotations.SerializedName
import com.sela.stackoverflowapp.data.model.QuestionModel

/**
 * stackoverflow response - the response that return when getting stackoverflowapp
 * parse from json
 */
data class StackOverFlowResponse (
    @SerializedName("has_more") val hasMore: Boolean,
    @SerializedName("items") val items: List<QuestionModel>,
    @SerializedName("quota_max") val quotaMax: Int,
    @SerializedName("quota_remaining") val quotaRemaining: Int,
)
