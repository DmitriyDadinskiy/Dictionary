package com.example.dictionary.model.source

import com.example.dictionary.model.data.TranslationListEntityItem

interface GivTranslationListRepo {
    fun getTranslationList(
        query: String,
        onSuccess: (result: List<TranslationListEntityItem>) -> Unit,
        onError: (Throwable) -> Unit,
    )
}