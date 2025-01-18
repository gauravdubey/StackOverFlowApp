package com.gaurav.stackoverflowapp.networking

import com.gaurav.stackoverflowapp.networking.question.QuestionDetailsSchema
import com.gaurav.stackoverflowapp.networking.question.QuestionListSchema
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/questions?key=$STACKOVERFLOW_API_KEY&sort=activity&order=desc&site=stackoverflow")
    suspend fun fetchLastActiveQuestions(@Query("page_size") pageSize: Int?): QuestionListSchema?

    @GET("/questions/{questionId}?key=$STACKOVERFLOW_API_KEY&site=stackoverflow&filter=withbody")
    suspend fun fetchQuestionDetails(@Path("questionId")questionId: String?): QuestionDetailsSchema?

    companion object {
        const val STACKOVERFLOW_API_KEY = "f)yov8mEGrYZa1dJDb2gpg(("
    }
}