package com.example.triviajson_august9.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.triviajson_august9.databinding.ListItemTriviaBinding
import com.example.triviajson_august9.model.wrapper.QuestionWithAnswer

class TriviaAdapter : RecyclerView.Adapter<TriviaAdapter.TriviaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaViewHolder {
        return TriviaViewHolder(
            ListItemTriviaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TriviaViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun updateList(questions: List<QuestionWithAnswer>) {
        differ.currentList.clear()
        differ.submitList(questions)
    }

    private val differ = AsyncListDiffer(
        this,
        diffCallback()
    )

    private fun diffCallback() = object : DiffUtil.ItemCallback<QuestionWithAnswer>() {
        override fun areItemsTheSame(
            oldItem: QuestionWithAnswer,
            newItem: QuestionWithAnswer
        ): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(
            oldItem: QuestionWithAnswer,
            newItem: QuestionWithAnswer
        ): Boolean {
            return oldItem == newItem
        }
    }

    class TriviaViewHolder(private val binding: ListItemTriviaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: QuestionWithAnswer) {
            with(binding) {
                tvQuestion.text = question.question

                question.answer.shuffled()
                val answerSize = question.answer.size

                answer1.apply {
                    isVisible = true
                    text = question.answer[0].answer
                }
                answer2.apply {
                    isVisible = true
                    text = question.answer[1].answer
                }
                if (answerSize > 2) {
                    answer3.apply {
                        isVisible = true
                        text = question.answer[2].answer
                    }
                }
                if (answerSize > 3) {
                    answer4.apply {
                        isVisible = answerSize > 3
                        text = question.answer[3].answer
                    }
                }
            }
        }
    }

}