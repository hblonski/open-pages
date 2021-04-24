package com.hb.pages.features

import android.app.Activity
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.hb.pages.R
import com.hb.pages.databinding.ActivityMainBinding
import com.hb.pages.extensions.hideKeyboard
import com.hb.pages.extensions.launch
import com.hb.pages.extensions.setVisible
import com.hb.pages.features.adapter.BooksAdapter
import com.hb.pages.model.Book
import com.hb.pages.util.LOADING_SPIN_ANIMATION
import com.hb.pages.view.decorators.MarginItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainLayoutContainer(
    private val binding: ActivityMainBinding,
    private val viewModel: MainViewModel,
    private val activity: Activity
) {

    fun init() {
        setupRecyclerView()
        setupSearchInput()
        setupLoadingAnimation()
    }

    fun setState(state: MainActivityState) {
        stopLoadingAnimation()
        setNotFoundInformationVisibility(
            !state.hasResults && !state.isFirstSearch && !state.hasConnectionError
        )
        setFirstSearchInformationVisibility(state.isFirstSearch)
        setConnectionErrorInformationVisibility(state.hasConnectionError)

        if (state is MainActivityState.Results) {
            setBooksList(state.results)
        }
    }


    private fun setBooksList(books: List<Book>) {
        with(binding.rvResults) {
            stopLoadingAnimation()
            if (adapter == null) {
                adapter = BooksAdapter(books)
            } else {
                setNotFoundInformationVisibility(books.isEmpty())
                (adapter as BooksAdapter).updateItems(books)
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvResults) {
            val bookTopMargin = activity.resources
                .getDimension(R.dimen.search_book_activity_book_margin_top).toInt()
            val decoration = MarginItemDecoration(topMargin = bookTopMargin)
            addItemDecoration(decoration)
            layoutManager = GridLayoutManager(activity, RESULTS_GRID_COLUMNS)
        }
    }

    private fun setupSearchInput() {
        binding.inputSearch.setOnEditorActionListener { _, actionId, _ ->
            setNotFoundInformationVisibility(false)
            return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                activity.hideKeyboard()
                setFirstSearchInformationVisibility(binding.inputSearch.text.isEmpty())
                if (binding.inputSearch.text.isNotEmpty()) {
                    playLoadingAnimation()
                }
                binding.rvResults.adapter?.let { adapter ->
                    (adapter as BooksAdapter).updateItems(emptyList())
                }
                viewModel.launch { search(binding.inputSearch.text.toString()) }
                true
            } else {
                false
            }
        }
    }

    private fun setupLoadingAnimation() {
        LottieCompositionFactory.fromAsset(activity, LOADING_SPIN_ANIMATION).addListener {
            binding.loadingAnimation.setComposition(it)
            binding.loadingAnimation.repeatMode = LottieDrawable.RESTART
        }
    }

    private fun playLoadingAnimation() {
        binding.loadingAnimation.setVisible(true)
        binding.loadingAnimation.playAnimation()
    }

    private fun stopLoadingAnimation() {
        binding.loadingAnimation.setVisible(false)
        binding.loadingAnimation.pauseAnimation()
    }

    private fun setNotFoundInformationVisibility(isVisible: Boolean) {
        binding.imageNotFound.setVisible(isVisible)
        binding.textNotFound.setVisible(isVisible)
    }

    private fun setFirstSearchInformationVisibility(isVisible: Boolean) {
        binding.imageFirstSearch.setVisible(isVisible)
        binding.textFirstSearch.setVisible(isVisible)
    }

    private fun setConnectionErrorInformationVisibility(isVisible: Boolean) {
        binding.noConnectionIcon.setVisible(isVisible)
        binding.textNoConnection.setVisible(isVisible)
    }

    companion object {
        private const val RESULTS_GRID_COLUMNS = 3
    }
}