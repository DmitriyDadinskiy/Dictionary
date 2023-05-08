package com.example.dictionary.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.ItemTranslatorListBinding
import com.example.dictionary.model.data.TranslationListEntityItem

class TranslationListHolder(
    item: View
) : RecyclerView.ViewHolder(item) {
    private val binding = ItemTranslatorListBinding.bind(item)

    fun bind(translationListEntityItem: TranslationListEntityItem) = with(binding) {
        itemWordTextView.text = translationListEntityItem.text
        itemTranslationTextView.text = translationListEntityItem.meanings[0].translation.text
        itemTranscriptionTextView.text= translationListEntityItem.meanings[0].transcription
    }
}