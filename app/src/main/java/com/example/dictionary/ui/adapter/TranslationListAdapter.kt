package com.example.dictionary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.model.data.TranslationListEntityItem

class TranslationListAdapter(
    private var translationListAdapter: List<TranslationListEntityItem>
) : RecyclerView.Adapter<TranslationListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationListHolder {
        val view = LayoutInflater.from((parent.context))
            .inflate(R.layout.item_translator_list, parent, false)
        return TranslationListHolder(view)
    }

    override fun getItemCount() = translationListAdapter.size

    override fun onBindViewHolder(holder: TranslationListHolder, position: Int) {
        holder.bind(translationListAdapter[position])
    }

    fun addWordTranslation(translationListAdapter: List<TranslationListEntityItem>) {
        this.translationListAdapter = translationListAdapter
        notifyItemChanged(-1)
    }


}