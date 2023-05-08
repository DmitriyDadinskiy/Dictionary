package com.example.dictionary.model.source

import com.example.dictionary.model.data.TranslationListEntityItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

class RetrofitGivTranslationListImpl : GivTranslationListRepo {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private var api: GivTranslationRepoApi = retrofit.create(GivTranslationRepoApi::class.java)

    override fun getTranslationList(
        query: String,
        onSuccess: (result: List<TranslationListEntityItem>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api.search(query).enqueue(object : Callback<List<TranslationListEntityItem>> {
            override fun onResponse(
                call: Call<List<TranslationListEntityItem>>,
                response: Response<List<TranslationListEntityItem>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError.invoke(Throwable())
                }
            }

            override fun onFailure(call: Call<List<TranslationListEntityItem>>, t: Throwable) {
                onError(t)
            }

        })
    }


}