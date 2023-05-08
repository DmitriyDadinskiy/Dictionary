package com.example.dictionary.presenter

import com.example.dictionary.model.data.TranslationListEntityItem
import com.example.dictionary.model.source.GivTranslationListRepo


class DictionaryPresenter(
    private val translationListRepo: GivTranslationListRepo
) : DictionaryContract.Presenter {
    private var view: DictionaryContract.View? = null
    private lateinit var word: String
    private var translationList: List<TranslationListEntityItem>? = null
    private var inProgress: Boolean = true
    override fun attach(view: DictionaryContract.View) {
        this.view = view
        view.showProgress(inProgress)
        translationList?.let{view.getListTranslation(it)}
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh(querySearchWord: String) {
        word = querySearchWord
        loadWordDictionary()
    }

    private fun loadWordDictionary() {
        view?.showProgress(false)
        inProgress = false
        translationListRepo.getTranslationList(
            query = word,
            onSuccess = {
                view?.showProgress(true)
                translationList = it
                view?.getListTranslation(it)
                inProgress = true
            },
            onError = {
                view?.showProgress(true)
                view?.loadingError(it)
                inProgress = true
            }
        )
    }

}