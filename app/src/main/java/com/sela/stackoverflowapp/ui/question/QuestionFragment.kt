package com.sela.stackoverflowapp.ui.question


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sela.stackoverflowapp.R
import com.sela.stackoverflowapp.ui.BaseFragment
import com.sela.stackoverflowapp.ui.feed.FeedViewModel
import com.sela.stackoverflowapp.ui.feed.FeedViewModelFactory
import com.sela.superdomarket.utils.logDebug
import kotlinx.android.synthetic.main.fragment_question.*


class QuestionFragment : BaseFragment() {

    override var resourceLayout = R.layout.fragment_question

    private val viewModel: QuestionViewModel by viewModels()

    companion object{
        const val QUESTION_LINK = "question_link"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionLink = arguments?.getString(QUESTION_LINK)
        logDebug("questionLink = $questionLink")

        viewModel.setQuestionLink(questionLink)

        initViews(view)
        initObservers()
    }


    override fun initViews(view: View) {

    }

    override fun initObservers() {
        viewModel.questionLink.observe(viewLifecycleOwner, Observer {
            question_web_view.loadUrl(it)
        })
    }


}