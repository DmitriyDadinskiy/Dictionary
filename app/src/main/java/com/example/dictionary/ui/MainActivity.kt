package com.example.dictionary.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.app
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.model.data.TranslationListEntityItem
import com.example.dictionary.presenter.DictionaryContract
import com.example.dictionary.presenter.DictionaryPresenter
import com.example.dictionary.ui.adapter.TranslationListAdapter

class MainActivity : AppCompatActivity(), DictionaryContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterTranslationList: TranslationListAdapter
    private var querySearchWord: String = ""
    private lateinit var presenter: DictionaryContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = DictionaryPresenter(app.givTranslationListRepo)
        (presenter as DictionaryPresenter).attach(this)
        showProgress(true)
        init()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun init() {
        clickOnSearch()
    }

    private fun clickOnSearch() {

        binding.mainActivityImageButtonSearch.setOnClickListener {
            querySearchWord = binding.mainActivityEditTextEnterWord.text.toString()
            presenter.onRefresh(querySearchWord)
            initViewTranslationList()
        }
    }

    private fun initViewTranslationList() {
        binding.apply {
            mainActivityListTranslatorRecyclerView.layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.VERTICAL, false
            )
            adapterTranslationList = TranslationListAdapter(listOf())
            mainActivityListTranslatorRecyclerView.adapter = adapterTranslationList

        }
    }

    override fun getListTranslation(result: List<TranslationListEntityItem>) {
        adapterTranslationList.addWordTranslation(result)
        showProgress(true)
    }

    override fun loadingError(throwable: Throwable) {
        Toast.makeText(this@MainActivity, "$throwable", Toast.LENGTH_LONG).show()
        showProgress(false)

    }

    override fun showProgress(inProgress: Boolean) {
        binding.mainLoadUsersProgressBar.isVisible = inProgress
        binding.mainLoadUsersProgressBar.isVisible = !inProgress
    }
}