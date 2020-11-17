package com.sela.stackoverflowapp.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sela.stackoverflowapp.R
import com.sela.stackoverflowapp.data.model.QuestionModel
import com.sela.stackoverflowapp.utils.getFullMonthYearClockFormat

/**
 * FeedAdapter - Adapter for display all questions items
 */
class FeedAdapter : PagedListAdapter<QuestionModel, FeedAdapter.FeedViewHolder>(DiffCallback()) {
    var clickListener: RecyclerViewClickListener? = null

    class FeedViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView){
        private val title = itemView.findViewById<TextView>(R.id.title_text)
        private val dateText = itemView.findViewById<TextView>(R.id.date_text)
        private val numberOfAnswersText = itemView.findViewById<TextView>(R.id.answers_text)
        private val scoreText = itemView.findViewById<TextView>(R.id.score_text)

        fun bind(item: QuestionModel, clickListener: RecyclerViewClickListener?) {
            title.text = item.title
            dateText.text = "${item.creationDate.getFullMonthYearClockFormat()} by ${item.owner.displayName}"
            numberOfAnswersText.text = "${item.answerCount} A"
            scoreText.text = "${item.score} S"
            itemView.setOnClickListener { clickListener?.onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.feed_item, parent, false))
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener)
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<QuestionModel>() {
        override fun areItemsTheSame(oldItem: QuestionModel, newItem: QuestionModel): Boolean {
            return oldItem.questionId == newItem.questionId
        }

        override fun areContentsTheSame(oldItem: QuestionModel, newItem: QuestionModel): Boolean {
            return oldItem == newItem
        }
    }

}
