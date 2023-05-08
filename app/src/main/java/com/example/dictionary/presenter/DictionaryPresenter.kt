package com.example.dictionary.presenter

import com.example.dictionary.model.source.GivTranslationListRepo


class DictionaryPresenter(
    private val translationListRepo: GivTranslationListRepo
) : DictionaryContract.Presenter {
    private var view: DictionaryContract.View? = null
    private lateinit var word: String

    override fun attach(view: DictionaryContract.View) {
        this.view = view
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
        translationListRepo.getTranslationList(
            query = word,
            onSuccess = {
                view?.showProgress(true)
                view?.getListTranslation(it)
            },
            onError = {
                view?.showProgress(true)
                view?.loadingError(it)
            }
        )
    }

}