package com.sela.stackoverflowapp.ui.feed

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sela.stackoverflowapp.R
import com.sela.stackoverflowapp.data.model.Filter
import com.sela.stackoverflowapp.data.model.QuestionModel
import com.sela.stackoverflowapp.ui.BaseFragment
import com.sela.stackoverflowapp.ui.question.QuestionFragment.Companion.QUESTION_LINK
import com.sela.superdomarket.utils.logError
import kotlinx.android.synthetic.main.fragment_feed.*


/**
 * Created by seladev
 */
class FeedFragment : BaseFragment(){

    override var resourceLayout: Int = R.layout.fragment_feed
    override var titleResource = R.string.questions

    private lateinit var viewModelFactory: FeedViewModelFactory
    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: FeedAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = FeedViewModelFactory(activity?.application!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FeedViewModel::class.java)

        initViews(view)
        initObservers()
    }



    override fun initViews(view: View) {

        feedAdapter = FeedAdapter()
        feedAdapter.clickListener = object: RecyclerViewClickListener{
            override fun onClick(item: QuestionModel) {
                val bundle = bundleOf(QUESTION_LINK to item.link )
                view.findNavController().navigate(R.id.action_feed_fragment_to_question_fragment, bundle)
            }
        }

        question_recycler_view.apply {
            this.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.adapter = feedAdapter
        }

        swipe_to_refresh_container.setOnRefreshListener {
            viewModel.refresh()
        }

        filter_toggle_group.addOnButtonCheckedListener{group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.all -> {
                        viewModel.setFilter(Filter.ALL)
                    }
                    R.id.answers -> {
                        viewModel.setFilter(Filter.ANSWERS)
                    }
                    R.id.unanswers -> {
                        viewModel.setFilter(Filter.UN_ANSWERS)
                    }
                }
            }
            else{
                viewModel.setFilter(Filter.ALL)
            }
        }

    }

    override fun initObservers() {
        viewModel.questionList.observe(viewLifecycleOwner, Observer {
            logError("questions $it")
            swipe_to_refresh_container.isRefreshing = false
            feedAdapter.submitList(it)
        })
    }
}