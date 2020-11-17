package com.sela.stackoverflowapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by seladev
 */
data class OwnerModel(@SerializedName("user_id") var userId:String,
                      @SerializedName("profile_image") var profileImage:String,
                      @SerializedName("display_name") var displayName:String,
                              ) {
}