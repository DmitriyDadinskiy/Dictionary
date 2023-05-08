package com.example.dictionary.model.data

data class TranslationListEntityItem(
    val id: Int,
    val meanings: List<Meaning>,
    val text: String
)