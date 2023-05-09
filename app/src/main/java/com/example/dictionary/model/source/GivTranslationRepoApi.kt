package com.example.dictionary.model.source

import com.example.dictionary.model.data.TranslationListEntityItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GivTranslationRepoApi {
    @GET("words/search")
    fun search(
        @Query("search") wordToSearch: String
    ): Call<List<TranslationListEntityItem>>
}