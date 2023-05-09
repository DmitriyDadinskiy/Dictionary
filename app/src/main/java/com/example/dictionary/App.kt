package com.example.dictionary

import android.app.Application
import android.content.Context
import com.example.dictionary.model.source.GivTranslationListRepo
import com.example.dictionary.model.source.RetrofitGivTranslationListImpl

class App : Application() {
    val givTranslationListRepo: GivTranslationListRepo by lazy { RetrofitGivTranslationListImpl() }

}

val Context.app get() = applicationContext as App