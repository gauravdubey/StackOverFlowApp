package com.gaurav.stackoverflowapp.networking.question

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionListSchema(
    @Json(name = "items")
    val questions: List<QuestionSchema>
)
