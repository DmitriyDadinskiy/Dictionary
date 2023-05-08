package com.example.dictionary.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.R
import com.example.dictionary.app
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.model.data.TranslationListEntityItem
import com.example.dictionary.model.source.GivTranslationListRepo
import com.example.dictionary.ui.adapter.TranslationListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterTranslationList: TranslationListAdapter
    private val givTranslationListRepo: GivTranslationListRepo by lazy { app.givTranslationListRepo }
    private var querySearchWord: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        clickOnSearch()

    }

    private fun clickOnSearch() {

        binding.mainActivityImageButtonSearch.setOnClickListener {
            querySearchWord = binding.mainActivityEditTextEnterWord.text.toString()
            initViewTranslationList(querySearchWord)
        }
    }

    private fun initViewTranslationList(querySearchWord: String) {
        binding.apply {
            mainActivityListTranslatorRecyclerView.layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.VERTICAL, false
            )
            adapterTranslationList = TranslationListAdapter(listOf())
            mainActivityListTranslatorRecyclerView.adapter = adapterTranslationList
            givTranslationListRepo.getTranslationList(
                query = querySearchWord,
                onSuccess = ::getListTranslation,
                onError = ::loadingError
            )
        }
    }

    private fun getListTranslation(result: List<TranslationListEntityItem>) {
        adapterTranslationList.addWordTranslation(result)
        showProgress(true)
    }

    private fun loadingError(throwable: Throwable) {
        Toast.makeText(this@MainActivity, "$throwable", Toast.LENGTH_LONG).show()
        Log.d(ContentValues.TAG, "ОШИБКА: $throwable")
        showProgress(false)

    }

    private fun showProgress(inProgress: Boolean) {
        binding.mainLoadUsersProgressBar.isVisible = inProgress
        binding.mainLoadUsersProgressBar.isVisible = !inProgress
    }
}