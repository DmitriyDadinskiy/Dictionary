package com.example.dictionary.presenter

import com.example.dictionary.model.data.TranslationListEntityItem


interface DictionaryContract {
    interface View{
        fun showProgress(inProgress: Boolean)
        fun getListTranslation(result: List<TranslationListEntityItem>)
        fun loadingError(throwable: Throwable)
    }
    interface Presenter{
        fun attach(view: View)
        fun detach()
        fun onRefresh(querySearchWord: String)
    }
}